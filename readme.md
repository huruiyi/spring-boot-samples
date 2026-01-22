# Spring Boot Samples

Spring Boot 技术示例项目集合，涵盖了企业级开发中常用的技术栈和最佳实践。

## 项目简介

本项目是一个 Spring Boot 学习和实践的示例集合，包含了 Spring Boot 与各种中间件、框架的集成示例。每个模块都是一个独立的项目，可以直接运行和学习。

## 技术栈

- **Spring Boot**: 2.7.18
- **Java**: 8+
- **构建工具**: Maven

## 项目结构

### 核心框架
- [spring-boot](spring-boot/) - Spring Boot 基础示例

### 数据访问
- [spring-boot-data](spring-boot-data/) - 数据访问模块集合
  - [spring-boot-data-mongodb](spring-boot-data/spring-boot-data-mongodb/) - MongoDB 集成
  - [spring-boot-data-mysql-v1](spring-boot-data/spring-boot-data-mysql-v1/) - MySQL 基础集成
  - [spring-boot-data-mysql-v2](spring-boot-data/spring-boot-data-mysql-v2/) - MySQL 进阶集成
  - [spring-boot-data-mysql-v3](spring-boot-data/spring-boot-data-mysql-v3/) - MySQL 批量操作
  - [spring-boot-data-nacos-datasource](spring-boot-data/spring-boot-data-nacos-datasource/) - Nacos 数据源
  - [spring-boot-data-redis-stock-lock](spring-boot-data/spring-boot-data-redis-stock-lock/) - Redis 分布式锁（库存扣减）
- [spring-boot-mybatis](spring-boot-mybatis/) - MyBatis 集成
- [spring-boot-mybatisplus-v1](spring-boot-mybatisplus-v1/) - MyBatis-Plus 基础示例
- [spring-boot-mybatisplus-v2](spring-boot-mybatisplus-v2/) - MyBatis-Plus 进阶示例
- [spring-boot-mybatisplus-v3](spring-boot-mybatisplus-v3/) - MyBatis-Plus 批量操作
- [spring-boot-mongo](spring-boot-mongo/) - MongoDB 文件上传示例
- [spring-multi-datasource](spring-multi-datasource/) - 多数据源配置

### 消息队列
- [spring-boot-activemq](spring-boot-activemq/) - ActiveMQ 消息队列
- [spring-boot-kafka](spring-boot-kafka/) - Kafka 消息队列
  - [kafka-provider](spring-boot-kafka/kafka-provider/) - 生产者
  - [kafka-consumer](spring-boot-kafka/kafka-consumer/) - 消费者
- [spring-boot-kafka-mysql-order](spring-boot-kafka-mysql-order/) - Kafka + MySQL 订单处理
- [spring-boot-kafka-ordering](spring-boot-kafka-ordering/) - Kafka 消息顺序处理

### 安全认证
- [spring-boot-security](spring-boot-security/) - Spring Security 基础（RSA/AES 加密）
- [spring-boot-security-jpa](spring-boot-security-jpa/) - Spring Security + JPA
- [spring-boot-security-ldap-jwt](spring-boot-security-ldap-jwt/) - Spring Security + LDAP + JWT
- [spring-boot-security-mybatis-plus](spring-boot-security-mybatis-plus/) - Spring Security + MyBatis-Plus
- [spring-boot-oauth2](spring-boot-oauth2/) - OAuth2 认证授权
  - [oauth2-memory](spring-boot-oauth2/oauth2-memory/) - 内存存储
  - [oauth2-jdbc](spring-boot-oauth2/oauth2-jdbc/) - JDBC 存储
  - [oauth2-redis-server](spring-boot-oauth2/oauth2-redis-server/) - Redis 存储
  - [oauth2-server](spring-boot-oauth2/oauth2-server/) - 授权服务器

### 监控运维
- [spring-boot-actuator](spring-boot-actuator/) - Spring Boot Actuator 监控
- [spring-boot-admin-client](spring-boot-admin-client/) - Spring Boot Admin 客户端
- [spring-boot-admin-server](spring-boot-admin-server/) - Spring Boot Admin 服务端
- [spring-boot-prometheus](spring-boot-prometheus/) - Prometheus 监控集成

### 缓存
- [spring-boot-redis](spring-boot-redis/) - Redis 缓存应用

### 工作流
- [spring-boot-flowable-pro](spring-boot-flowable-pro/) - Flowable 工作流（专业版）
- [spring-boot-flowable-simple](spring-boot-flowable-simple/) - Flowable 工作流（简化版）

### RPC 框架
- [spring-boot-dubbo](spring-boot-dubbo/) - Dubbo3 RPC 框架
  - [dubbo3-interface](spring-boot-dubbo/dubbo3-interface/) - 接口定义
  - [dubbo3-provider](spring-boot-dubbo/dubbo3-provider/) - 服务提供者
  - [dubbo3-consumer](spring-boot-dubbo/dubbo3-consumer/) - 服务消费者

### Web 开发
- [spring-boot-thymeleaf](spring-boot-thymeleaf/) - Thymeleaf 模板引擎
- [spring-boot-websocket](spring-boot-websocket/) - WebSocket 实时通信
  - [websocket-basic](spring-boot-websocket/websocket-basic/) - 基础 WebSocket
  - [websocket-sock-js](spring-boot-websocket/websocket-sock-js/) - SockJS 支持
  - [websocket-stomp](spring-boot-websocket/websocket-stomp/) - STOMP 协议
  - [websocket-chatroom](spring-boot-websocket/websocket-chatroom/) - 聊天室示例
- [spring-boot-openapi](spring-boot-openapi/) - OpenAPI/Swagger 文档

### 其他
- [spring-boot-batch](spring-boot-batch/) - Spring Batch 批处理
- [spring-boot-docker](spring-boot-docker/) - Docker 部署
- [spring-boot-self-starter-test](spring-boot-self-starter-test/) - 自定义 Starter 示例

## 快速开始

### 环境要求

- JDK 8+
- Maven 3.6+
- MySQL 5.7+ / 8.0+（部分模块需要）
- Redis 5.0+（部分模块需要）
- Kafka 2.0+（部分模块需要）

### 编译项目

```bash
mvn clean install
```

### 运行单个模块

进入对应模块目录，执行：

```bash
mvn spring-boot:run
```

或者直接运行主类：

```bash
java -jar target/xxx-0.0.1.jar
```

## 模块说明

### 数据访问模块

#### MyBatis 系列
- **spring-boot-mybatis**: MyBatis 基础集成，包含 TypeHandler、插件开发等
- **spring-boot-mybatisplus-v1**: MyBatis-Plus 基础 CRUD 操作
- **spring-boot-mybatisplus-v2**: MyBatis-Plus 代码生成器
- **spring-boot-mybatisplus-v3**: MyBatis-Plus 批量操作优化

#### Redis 系列
- **spring-boot-redis**: Redis 基础应用，包含缓存、Lua 脚本等
- **spring-boot-data-redis-stock-lock**: Redis 分布式锁实现库存扣减

### 安全认证模块

#### Spring Security 系列
- **spring-boot-security**: RSA/AES 加密解密示例
- **spring-boot-security-jpa**: 基于 JPA 的用户认证
- **spring-boot-security-ldap-jwt**: LDAP 认证 + JWT 令牌
- **spring-boot-security-mybatis-plus**: 基于 MyBatis-Plus 的用户认证

#### OAuth2 系列
- **oauth2-memory**: 基于内存的 OAuth2 授权服务器
- **oauth2-jdbc**: 基于 JDBC 的 OAuth2 授权服务器
- **oauth2-redis-server**: 基于 Redis 的 OAuth2 授权服务器
- **oauth2-server**: OAuth2 资源服务器

### 消息队列模块

#### Kafka 系列
- **spring-boot-kafka**: Kafka 基础生产者和消费者
- **spring-boot-kafka-mysql-order**: Kafka + MySQL 订单处理示例
- **spring-boot-kafka-ordering**: Kafka 消息顺序保证

#### 其他
- **spring-boot-activemq**: ActiveMQ 点对点和发布订阅模式

### 监控运维模块

- **spring-boot-actuator**: 应用健康检查、指标监控
- **spring-boot-admin-server**: Spring Boot Admin 监控服务端
- **spring-boot-admin-client**: Spring Boot Admin 监控客户端
- **spring-boot-prometheus**: Prometheus 指标采集

## 常见问题

### 数据库连接配置

各模块的数据库配置位于 `src/main/resources/application.properties` 或 `application.yml`，请根据实际情况修改。

### 端口冲突

如果端口冲突，可以在配置文件中修改 `server.port`。

### 依赖版本

项目统一使用 Spring Boot 2.7.18，各依赖版本由 Spring Boot 管理。

## 参考资源

- [Spring Boot 官方文档](https://spring.io/projects/spring-boot)
- [Spring Security 官方文档](https://spring.io/projects/spring-security)
- [MyBatis-Plus 官方文档](https://baomidou.com/)
- [Flowable 官方文档](https://www.flowable.com/open-source/docs)

## 许可证

本项目采用 Apache License 2.0 许可证。

## 贡献

欢迎提交 Issue 和 Pull Request！

---

# Apache HttpClient 
1. [Quick Start](https://hc.apache.org/httpcomponents-client-5.2.x/quickstart.html) - contains simple, complete examples of request execution with the classic, fluent and async APIs.
2. Examples demonstrating some common as well as more complex use cases
   - [HttpClient (classic APIs)](https://hc.apache.org/httpcomponents-client-5.2.x/examples.html)
   - [HttpClient (async APIs)](https://hc.apache.org/httpcomponents-client-5.2.x/examples-async.html)
   - [HttpClient (reactive APIs)](https://hc.apache.org/httpcomponents-client-5.2.x/examples-reactive.html)
3. Javadocs
   - [HttpClient](https://hc.apache.org/httpcomponents-client-5.2.x/current/httpclient5/apidocs/)
   - [HC Fluent](https://hc.apache.org/httpcomponents-client-5.2.x/current/httpclient5-fluent/apidocs/)
   - [HttpClient Cache](https://hc.apache.org/httpcomponents-client-5.2.x/current/httpclient5-cache/apidocs/)
   - [HttpClient Windows extensions](https://hc.apache.org/httpcomponents-client-5.2.x/current/httpclient5-win/apidocs/)
4. API compatibility reports
   - [HttpClient](https://hc.apache.org/httpcomponents-client-5.2.x/current/httpclient5/japicmp.html)
   - [HC Fluent](https://hc.apache.org/httpcomponents-client-5.2.x/current/httpclient5-fluent/japicmp.html)
   - [HttpClient Cache](https://hc.apache.org/httpcomponents-client-5.2.x/current/httpclient5-cache/japicmp.html)
   - [HttpClient Windows extensions](https://hc.apache.org/httpcomponents-client-5.2.x/current/httpclient5-win/japicmp.html)

# Apache HttpAsyncClient 
1. [Quick Start](https://hc.apache.org/httpcomponents-asyncclient-4.1.x/quickstart.html) - contains a simple, complete example of asynchronous request execution.
2. [HttpAsyncClient Examples](https://hc.apache.org/httpcomponents-asyncclient-4.1.x/examples.html) - a set of examples demonstrating some of the more complex use scenarios.
3. Javadocs
   - [HttpAsyncClient](https://hc.apache.org/httpcomponents-asyncclient-4.1.x/current/httpasyncclient/apidocs/)
   - [HttpAsyncClient Cache](https://hc.apache.org/httpcomponents-asyncclient-4.1.x/current/httpasyncclient-cache/apidocs/)

# Apache httpcomponents svn
[httpcomponents svn](http://svn.apache.org/repos/asf/httpcomponents/)
### BasicAsyncRequestProducer,HttpAsyncRequestConsumer

# TreeSet特点
TreeSet是用来排序的, 可以指定一个顺序, 对象存入之后会按照指定的顺序排列
使用方式

### 自然顺序(Comparable)
TreeSet类的add()方法中会把存入的对象提升为Comparable类型
调用对象的compareTo()方法和集合中的对象比较
根据compareTo()方法返回的结果进行存储

### 比较器顺序(Comparator)
创建TreeSet的时候可以制定 一个Comparator
如果传入了Comparator的子类对象, 那么TreeSet就会按照比较器中的顺序排序
add()方法内部会自动调用Comparator接口中compare()方法排序
调用的对象是compare方法的第一个参数,集合中的对象是compare方法的第二个参数

### 两种方式的区别
TreeSet构造函数什么都不传, 默认按照类中Comparable的顺序(没有就报错ClassCastException)
TreeSet如果传入Comparator, 就优先按照Comparator

# [characterEncoding编码](https://dev.mysql.com/doc/refman/8.0/en/charset-charsets.html)

```shell
show character set 
show variables like '%character%'
set character_set_server = utf8mb4
```

|     MySQL Character Set Name      | **Java-Style Character Encoding Name** |
|:---------------------------------:|:--------------------------------------:|
| *For 8.0.12 and earlier*: `utf8`  |                 UTF-8                  |
| *For 8.0.13 and later*: `utf8mb4` |                 UTF-8                  |
|              gb2312               |                 EUC_CN                 |
|                gbk                |                  GBK                   |
|               ascii               |                US-ASCII                |

|     排序规则|                   说明      |
| :----------------: | :---------------------------------------: |
| utf8_general_ci    | 不区分大小写的比较                          |
| utf8_unicode_ci    | 区分大小写的比较                          |
| utf8mb4_general_ci | 不区分大小写的比较，支持 Emoji 等特殊字符 |
| utf8mb4_unicode_ci | 区分大小写的比较，支持 Emoji 等特殊字符   |

———————————————————————————————————————————————————————————————————————
## spring-boot-thin
```xml
<dependency>
    <groupId>org.springframework.boot.experimental</groupId>
    <artifactId>spring-boot-thin-layout</artifactId>
</dependency>
```

spring-boot-redis
```
redis的应用，以及缓存的使用
```

## CSDN博主
  - https://blog.csdn.net/gzu_01
  - https://stalin.blog.csdn.net/
  - https://blog.csdn.net/ysf15609260848


## howtodoinjava
- Github Source:[lokeshgupta1981/Spring-Boot-Examples](https://github.com/lokeshgupta1981/Spring-Boot-Examples/tree/master)
- [10 Life Lessons I have Learned in the Last Few Years](https://howtodoinjava.com/resources/10-life-lessons-i-have-learned-in-last-few-years/)
- [Spring Batch MultiResourceItemReader Example](https://howtodoinjava.com/spring-batch/multiresourceitemreader-read-multiple-csv-files-example/)
- [Log Request and Response with Spring RestTemplate](https://howtodoinjava.com/spring-boot2/resttemplate/clienthttprequestinterceptor/)
- [Masking Sensitive Data with Logback](https://howtodoinjava.com/logback/masking-sensitive-data/)
- [Log4j2 JSON Configuration Example (howtodoinjava.com)](https://howtodoinjava.com/log4j2/log4j2-json-configuration-example/)
- [Log4j2 - Maven and Gradle Configuration (howtodoinjava.com)](https://howtodoinjava.com/log4j2/maven-gradle-config/)
- [Setting Up TestNG in Eclipse (howtodoinjava.com)](https://howtodoinjava.com/testng/testng-tutorial-with-eclipse/)
- [How to Setup CI/CD Jenkins Pipeline for a Spring Boot Application (howtodoinjava.com)](https://howtodoinjava.com/devops/setup-jenkins-pipeline-for-spring-boot-app/)

