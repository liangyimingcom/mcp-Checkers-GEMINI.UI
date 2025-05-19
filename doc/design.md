# 跳跳棋活动系统架构设计

## 1. 系统架构概述

跳跳棋活动系统是基于Java微服务架构的营销活动平台，用于提升用户送礼消耗和平台活跃度。系统核心功能包括跳跳棋游戏、宝箱兑换和活动排行榜。

### 技术栈选择

- **后端框架**：Spring Boot + Spring Cloud
- **消息队列**：RabbitMQ/Kafka
- **数据库**：MySQL + Redis
- **服务发现**：Eureka/Nacos
- **API网关**：Spring Cloud Gateway
- **监控**：Prometheus + Grafana
- **日志**：ELK
- **容器化**：Docker + Kubernetes

## 2. 微服务架构

1. **游戏微服务(Game Service)**
   - 管理游戏核心逻辑
   - 处理掷骰子、角色移动、奖励发放等功能
   - 维护用户游戏进度和状态

2. **奖励微服务(Reward Service)**
   - 管理宝箱和积分兑换
   - 处理奖励发放逻辑

3. **排行榜微服务(Leaderboard Service)**
   - 维护和更新用户积分排行榜
   - 实时计算排名

4. **配置微服务(Config Service)**
   - 管理游戏配置、宝箱配置等
   - 提供配置参数接口

## 3. 核心领域模型

主要实体：
- **User**：用户信息、游戏状态、积分等
- **GameRecord**：游戏记录、骰子结果、奖励信息
- **GameBoard**：游戏棋盘、格子配置
- **BoardSquare**：棋盘格子、奖励设置
- **TreasureChest**：宝箱配置、奖励内容
- **UserLeaderboard**：用户排名、积分
- **ActivityConfig**：活动配置、时间、规则

## 4. 数据库设计

主要表结构：
- **ACTIVITY_USER**：用户活动状态数据
- **GAME_RECORD**：游戏记录
- **BOARD_CONFIG**：棋盘格子配置
- **CHEST_CONFIG**：宝箱配置
- **ITEM_CONFIG**：道具配置
- **USER_LEADERBOARD**：排行榜数据
- **ACTIVITY_CONFIG**：活动配置

## 5. 业务流程设计

### 跳跳棋游戏流程
1. 用户赠送礼物
2. 礼物服务发布礼物事件
3. 游戏服务增加游戏机会
4. 用户使用游戏机会(掷骰子)
5. 游戏服务执行游戏逻辑
6. 发放奖励、积分并更新排行榜

### 宝箱兑换流程
1. 用户请求兑换宝箱
2. 验证积分是否足够
3. 扣减积分并记录兑换
4. 发放宝箱奖励

## 6. API接口设计

主要API：
1. 用户游戏状态API
2. 执行游戏操作API
3. 宝箱兑换API
4. 排行榜查询API
5. 活动配置API

## 7. 性能与可扩展性设计

- Redis缓存热点数据
- 服务无状态设计，支持水平扩展
- 分布式锁保证并发安全
- 消息队列异步处理事件