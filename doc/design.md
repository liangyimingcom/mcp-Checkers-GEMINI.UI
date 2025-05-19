# 跳跳棋活动系统架构设计

## 目录

- [跳跳棋活动系统架构设计](#跳跳棋活动系统架构设计)
  - [目录](#目录)
  - [1. 系统架构概述](#1-系统架构概述)
    - [整体架构](#整体架构)
    - [技术栈选择](#技术栈选择)
  - [2. 微服务架构](#2-微服务架构)
    - [微服务职责划分](#微服务职责划分)
  - [3. 核心领域模型](#3-核心领域模型)
    - [类图（核心领域对象）](#类图核心领域对象)
    - [核心枚举定义](#核心枚举定义)
  - [4. 数据库设计](#4-数据库设计)
    - [ER图](#er图)
    - [宽表设计说明](#宽表设计说明)
  - [5. 业务流程设计](#5-业务流程设计)
    - [跳跳棋游戏流程](#跳跳棋游戏流程)
    - [宝箱兑换流程](#宝箱兑换流程)
    - [排行榜更新流程](#排行榜更新流程)
  - [6. 接口交互设计](#6-接口交互设计)
    - [系统间通信模式](#系统间通信模式)
    - [消息事件设计](#消息事件设计)
  - [7. 关键技术实现](#7-关键技术实现)
    - [数据一致性保证](#数据一致性保证)
    - [高频排行榜优化方案](#高频排行榜优化方案)

## 1. 系统架构概述

跳跳棋活动系统是一个基于Java微服务架构的营销活动平台，用于提升用户送礼消耗和平台活跃度。系统核心功能包括跳跳棋游戏、宝箱兑换和活动排行榜。

### 整体架构

```mermaid
graph TD
    User[用户] --> FE[前端界面]
    FE --> GW[API网关]
    GW --> GameService[游戏服务]
    GW --> RewardService[奖励服务]
    GW --> LeaderboardService[排行榜服务]
    
    GameService --> GameDB[(游戏数据库)]
    RewardService --> RewardDB[(奖励数据库)]
    LeaderboardService --> LeaderboardDB[(排行榜数据库)]
    
    %% 外部依赖服务
    GameService --> MQ[消息队列]
    MQ --> UserService[用户服务]
    MQ --> PaymentService[支付服务]
    MQ --> GiftService[礼物服务]
    MQ --> NotificationService[通知服务]
    
    UserService --> UserDB[(用户数据库)]
    PaymentService --> PaymentDB[(支付数据库)]
    GiftService --> GiftDB[(礼物数据库)]
    
    class UserService,PaymentService,GiftService,NotificationService,UserDB,PaymentDB,GiftDB externalSystem;
    
    classDef externalSystem fill:#f9f,stroke:#333,stroke-width:2px;
```

### 技术栈选择

- **后端框架**：Spring Boot + Spring Cloud
- **消息队列**：RabbitMQ/Kafka
- **数据库**：MySQL (主要业务数据) + Redis (缓存、排行榜)
- **服务发现与注册**：Eureka/Nacos
- **API网关**：Spring Cloud Gateway
- **监控**：Spring Boot Admin + Prometheus + Grafana
- **日志**：ELK (Elasticsearch, Logstash, Kibana)
- **容器化部署**：Docker + Kubernetes

## 2. 微服务架构

跳跳棋活动系统采用微服务架构，将系统功能拆分为多个独立的微服务。

```mermaid
graph LR
    API[API网关] --> GameMS[游戏微服务]
    API --> RewardMS[奖励微服务]
    API --> LeaderboardMS[排行榜微服务]
    API --> ConfigMS[配置微服务]
    
    GameMS --> DB[(MySQL)]
    RewardMS --> DB
    LeaderboardMS --> DB
    LeaderboardMS --> Cache[(Redis)]
    ConfigMS --> DB
    
    GameMS -- 事件发布 --> MQ[消息队列]
    MQ -- 事件订阅 --> RewardMS
    MQ -- 事件订阅 --> LeaderboardMS
    
    %% 服务依赖关系
    GameMS -.-> UserExt[用户服务]
    GameMS -.-> GiftExt[礼物服务]
    RewardMS -.-> NotificationExt[通知服务]
    
    class UserExt,GiftExt,NotificationExt externalDependency;
    classDef externalDependency fill:#f9f,stroke:#333,stroke-width:1px,stroke-dasharray: 5 5;
```

### 微服务职责划分

1. **游戏微服务 (Game Service)**
   - 管理游戏核心逻辑
   - 处理掷骰子、角色移动、奖励发放等功能
   - 维护用户游戏进度和状态

2. **奖励微服务 (Reward Service)**
   - 管理宝箱和积分兑换
   - 处理奖励发放逻辑

3. **排行榜微服务 (Leaderboard Service)**
   - 维护和更新用户积分排行榜
   - 实时计算排名

4. **配置微服务 (Config Service)**
   - 管理游戏配置、宝箱配置等
   - 提供配置参数接口

## 3. 核心领域模型

### 类图（核心领域对象）

```mermaid
classDiagram
    class User {
        +Long userId                   // 用户ID
        +String username               // 用户名
        +Integer currentPosition       // 当前位置
        +Integer gameChances           // 游戏机会
        +Integer points                // 积分
        +Integer dailyPlays            // 每日已玩次数
        +Date lastPlayTime             // 最后游戏时间
        +Map~String, Boolean~ itemsUnlocked   // 已解锁道具
        +Map~String, Boolean~ chestsRedeemed  // 已兑换宝箱
    }
    
    class GameRecord {
        +Long recordId                 // 记录ID
        +Long userId                   // 用户ID
        +Integer chancesUsed           // 使用机会数
        +List~Integer~ diceResults     // 骰子结果
        +Integer startPosition         // 起始位置
        +Integer endPosition           // 结束位置
        +Integer pointsGained          // 获得积分
        +List~RewardItem~ rewardsGained // 获得奖励
        +Date createTime               // 创建时间
    }
    
    class GameBoard {
        +Integer totalSquares          // 总格子数
        +List~BoardSquare~ squares     // 格子列表
    }
    
    class BoardSquare {
        +Integer position              // 位置
        +SquareType type               // 格子类型
        +String name                   // 名称
        +String icon                   // 图标
        +Integer value                 // 奖励值
        +String itemKey                // 道具键名
    }
    
    class TreasureChest {
        +String chestId                // 宝箱ID
        +String name                   // 宝箱名称
        +Integer pointsRequired        // 所需积分
        +String reward                 // 奖励描述
        +String icon                   // 图标
        +String cssClass               // CSS类名
    }
    
    class RewardItem {
        +String itemKey                // 道具键名
        +String itemName               // 道具名称
        +ItemType itemType             // 道具类型
        +Integer pointsEquivalent      // 积分等价值
    }
    
    class UserLeaderboard {
        +Long userId                   // 用户ID
        +String username               // 用户名
        +Integer points                // 积分
        +Integer rank                  // 排名
        +Date updateTime               // 更新时间
    }
    
    class ActivityConfig {
        +String activityId             // 活动ID
        +Date startTime                // 开始时间
        +Date endTime                  // 结束时间
        +Integer boardSize             // 棋盘大小
        +Integer maxChancesPerRoll     // 单次最大使用机会
        +Integer dailyPlayLimit        // 每日游戏次数限制
        +Boolean isActive              // 是否激活
    }

    User "1" -- "*" GameRecord : 产生
    GameBoard "1" -- "*" BoardSquare : 包含
    GameRecord "1" -- "*" RewardItem : 获得
    User "1" -- "1" UserLeaderboard : 拥有
```

### 核心枚举定义

```mermaid
classDiagram
    class SquareType {
        <<enumeration>>
        START
        POINTS
        ITEM
        HOME
    }
    
    class ItemType {
        <<enumeration>>
        AVATAR_FRAME
        ENTRY_EFFECT
        VIP
        MYSTERY_PACK
        OTHER
    }
    
    class ChestLevel {
        <<enumeration>>
        BRONZE
        SILVER
        GOLD
    }
```

## 4. 数据库设计

系统采用宽表模式设计数据库，减少表间关联，优化查询性能。

### ER图

```mermaid
erDiagram
    ACTIVITY_USER {
        bigint id PK
        bigint user_id
        int current_position
        int game_chances
        int points
        int daily_plays
        datetime last_play_time
        varchar unlocked_items
        varchar redeemed_chests
        datetime create_time
        datetime update_time
        tinyint is_deleted
    }
    
    GAME_RECORD {
        bigint id PK
        bigint user_id FK
        int chances_used
        varchar dice_results
        int start_position
        int end_position
        int points_gained
        varchar rewards_gained
        varchar activity_id
        datetime create_time
        datetime update_time
        tinyint is_deleted
    }
    
    BOARD_CONFIG {
        int id PK
        varchar activity_id
        int position
        varchar square_type
        varchar name
        varchar icon
        int value
        varchar item_key
        datetime create_time
        datetime update_time
        tinyint is_deleted
    }
    
    CHEST_CONFIG {
        int id PK
        varchar chest_id
        varchar activity_id
        varchar name
        int points_required
        varchar reward
        varchar icon
        varchar css_class
        datetime create_time
        datetime update_time
        tinyint is_deleted
    }
    
    ITEM_CONFIG {
        int id PK
        varchar item_key
        varchar activity_id
        varchar item_name
        varchar item_type
        int points_equivalent
        datetime create_time
        datetime update_time
        tinyint is_deleted
    }
    
    USER_LEADERBOARD {
        bigint id PK
        bigint user_id
        varchar activity_id
        varchar username
        int points
        int rank
        datetime update_time
        tinyint is_deleted
    }
    
    ACTIVITY_CONFIG {
        int id PK
        varchar activity_id
        datetime start_time
        datetime end_time
        int board_size
        int max_chances_per_roll
        int daily_play_limit
        tinyint is_active
        datetime create_time
        datetime update_time
        tinyint is_deleted
    }

    ACTIVITY_USER ||--o{ GAME_RECORD : "产生"
    ACTIVITY_USER ||--|| USER_LEADERBOARD : "关联"
```

### 宽表设计说明

1. **ACTIVITY_USER表**：
   - 保存用户在活动中的状态数据
   - 使用JSON字符串存储解锁道具和已兑换宝箱等集合数据
   - 避免多表关联，提高查询效率

2. **GAME_RECORD表**：
   - 记录用户每次游戏的详细信息
   - 使用JSON字符串存储骰子结果和奖励信息

3. **配置表**：
   - BOARD_CONFIG、CHEST_CONFIG、ITEM_CONFIG等表存储活动配置
   - 使用activity_id关联特定活动实例

4. **USER_LEADERBOARD表**：
   - 存储用户排行榜数据
   - 定期更新rank字段，避免实时排序