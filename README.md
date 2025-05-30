# 跳跳棋活动系统

## 项目简介

跳跳棋活动系统是一个基于Java微服务架构开发的直播平台营销活动系统。该系统通过游戏化方式提升用户在直播平台的参与度与互动性，以掷骰子前进、收集奖励的跳跳棋游戏形式，鼓励用户送礼并参与互动。

系统核心功能包括：
1. **跳跳棋游戏** - 用户通过送出指定礼物获得游戏机会，掷骰子决定角色前进步数，不同格子提供不同奖励
2. **宝箱兑换** - 用户通过游戏获得积分，解锁不同等级宝箱领取奖励
3. **排行榜系统** - 展示活动期间积分排名，促进用户竞争

项目采用Java 17 + Spring Boot 3.2.3开发，基于微服务架构设计，支持高并发、可扩展、高可用的业务场景。系统集成了多项技术实践，包括MyBatis持久层框架、Redis缓存、消息队列等，实现了完整的数据访问层、服务层和控制器层。

## doc目录文件说明

### requirement.md

产品需求文档，详细描述了跳跳棋活动的业务需求和功能规格：
- 活动概述与业务目标
- 完整活动规则，包括游戏机制、宝箱兑换机制及排行榜机制
- 各个微服务的职责划分与边界
- 架构师对实现的关键技术注释

### design.md

系统设计文档，包含了跳跳棋活动系统的详细技术设计方案：
- 系统整体架构设计
- 核心业务流程图
- 模块划分与功能清单
- 数据模型设计
- API接口设计
- 缓存设计
- 消息队列设计
- 高可用与扩展性设计

### prototype.jpg

UI原型图，展示了跳跳棋活动的前端界面设计，包括：
- 棋盘布局与视觉效果
- 用户操作界面元素
- 宝箱兑换与排行榜展示
- 积分和游戏机会显示

### schema.sql

数据库结构定义文件，包含系统所有表结构的创建脚本：
- 活动配置表 (demo_activity_config)
- 活动用户表 (demo_activity_user)
- 游戏记录表 (demo_game_record)
- 棋盘配置表 (demo_board_config)
- 宝箱配置表 (demo_chest_config)
- 道具配置表 (demo_item_config)
- 用户排行榜表 (demo_user_leaderboard)
- 事件表 (demo_event_record)

同时包含了必要的初始化示例数据，为开发和测试提供了基础数据支持。

### specification.md

技术规范文档，指导开发团队遵循统一的标准和最佳实践：
- 项目架构规范 (技术栈、分层架构)
- 代码组织规范 (包结构、命名约定)
- Java编码规范 (基本规范、命名规范、注释规范)
- API设计规范 (RESTful API、请求参数、响应规范)
- 异常处理规范 (异常体系、处理原则、错误码)
- 数据库规范 (命名规范、字段规范、索引规范)
- 缓存使用规范 (Redis缓存、缓存策略)
- 消息队列规范 (消息结构、消息处理)
- 日志规范 (日志级别、日志内容)
- 测试规范 (单元测试、测试数据、自动化测试)

### testcase.md

测试用例文档，详细描述了系统的测试策略和具体测试用例：
- 单元测试用例
- 接口测试用例
- 集成测试用例
- 性能测试方案
- 测试数据准备指南
- 测试覆盖率要求
- 测试环境配置说明
- 自动化测试脚本说明

## 根目录特殊文件说明

### prototype-v4.html

可交互的前端UI原型，实现了基本的游戏操作流程和视觉效果：
- 完整的游戏流程演示
- 游戏格子交互效果
- 宝箱兑换界面
- 排行榜显示功能

作为前端的参考实现，为后端开发提供了明确的业务流程和接口规范，有助于理解整个活动的业务逻辑和用户体验设计。