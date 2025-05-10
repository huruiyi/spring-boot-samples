在Spring Boot Data Redis里，从**2.0.0版本**起，Jedis客户端就被官方标记为已废弃，转而推荐使用Lettuce作为默认客户端。

### 主要变动情况
1. **版本变更**：Spring Boot 2.0.0（对应Spring Data Redis 2.0.0）开始不再把Jedis当作默认客户端。
2. **替代方案**：采用基于Netty的Lettuce客户端，它具备响应式编程、连接池管理更高效等诸多优势。
3. **依赖调整**：如果你的项目需要继续使用Jedis，得手动添加相关依赖。

### 依赖配置示例
```xml
<!-- 使用Lettuce（默认） -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>

<!-- 若要使用Jedis，需排除Lettuce并添加Jedis依赖 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
    <exclusions>
        <exclusion>
            <groupId>io.lettuce</groupId>
            <artifactId>lettuce-core</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
</dependency>
```

### 版本对应关系
| Spring Boot 版本 | 默认 Redis 客户端 |
|------------------|-------------------|
| 1.x              | Jedis             |
| 2.0.0 及更高版本 | Lettuce           |

建议优先选用Lettuce，因为它能更好地适配Spring的响应式编程模型。要是项目对Jedis有强依赖，就得手动配置依赖并留意维护方面的风险。
