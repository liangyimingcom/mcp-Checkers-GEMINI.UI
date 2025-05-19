# 跳跳棋-GEMINI.UI

这是一个基于Spring Boot的跳跳棋游戏活动系统。

## 项目结构

- `doc/`: 项目文档，包括需求、设计和测试用例
- `src/main/java/`: Java源代码
- `src/main/resources/`: 配置文件和资源
- `src/test/`: 测试代码

## 技术栈

- Java 17
- Spring Boot 3.2.3
- MyBatis
- MySQL/H2(测试)
- Redis
- AWS SQS

## 构建运行

```bash
./mvnw clean package
java -jar target/jumping-game-0.0.1-SNAPSHOT.jar
```