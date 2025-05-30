
以下是关于 **Spring Boot 集成 Prometheus** 的详细介绍，包括基本概念、集成步骤、常用配置和扩展场景：


### **一、核心概念**
#### 1. **Prometheus**
- **开源监控系统**：由 SoundCloud 开发，采用拉取（Pull）模式收集指标，支持多维度数据模型和 PromQL 查询语言。
- **核心组件**：Prometheus Server（数据存储与查询）、Exporter（指标采集器）、Alertmanager（告警管理）。

#### 2. **Spring Boot 与 Prometheus 的集成逻辑**
- 通过 **Micrometer** 框架（Spring Boot 内置的监控抽象层）将应用指标暴露为 Prometheus 兼容的格式。
- 使用 **Prometheus Exporter**（通常是一个 HTTP 端点）供 Prometheus Server 定期拉取指标。


### **二、集成步骤**
#### 1. 添加依赖
在 `pom.xml` 中引入 Micrometer 和 Prometheus 相关依赖：
```xml
<dependencies>
    <!-- Spring Boot 监控核心 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <!-- Prometheus 指标暴露 -->
    <dependency>
        <groupId>io.micrometer</groupId>
        <artifactId>micrometer-registry-prometheus</artifactId>
    </dependency>
</dependencies>
```

#### 2. 配置 Actuator 端点
在 `application.properties` 或 `application.yml` 中启用 Prometheus 端点并配置访问路径：
```yaml
# 启用所有 Actuator 端点（生产环境建议按需启用）
management:
  endpoints:
    web:
      exposure:
        include: "*"
  endpoint:
    prometheus:
      enabled: true  # 启用 Prometheus 端点
  metrics:
    export:
      prometheus:
        enabled: true  # 启用 Prometheus 指标导出
        step: 10s      # 指标刷新间隔（默认 1 分钟）
```

#### 3. 验证端点
启动应用后，访问 `http://localhost:8080/actuator/prometheus`，应看到类似以下格式的指标数据：
```plaintext
# HELP jvm_memory_used_bytes Used bytes of a given JVM memory area
# TYPE jvm_memory_used_bytes gauge
jvm_memory_used_bytes{area="heap",id="PS Eden Space",} 1.2345e+08
# HELP http_server_requests_seconds Time taken to process an HTTP request
# TYPE http_server_requests_seconds summary
http_server_requests_seconds{method="GET",uri="/actuator/prometheus",status="200",} 0.012,0.023,0.034
```


### **三、常用指标类型与配置**
#### 1. **内置指标**
Micrometer 自动收集以下类型的指标（可通过 `management.metrics.enable` 开关控制）：
- **JVM 指标**：内存、垃圾回收、线程数等。
- **HTTP 服务器指标**：请求耗时、吞吐量、错误率（需结合 Spring MVC/Reactive）。
- **数据库指标**：如 JDBC 连接池、HikariCP 状态（需引入对应数据库依赖）。
- **应用自定义指标**：需手动创建 `Counter`、`Gauge` 等。

#### 2. **自定义指标**
在服务类中注入 `MeterRegistry`，手动创建指标：
```java
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final Counter orderCounter;

    public OrderService(MeterRegistry registry) {
        // 创建计数器：统计订单创建次数，带标签
        orderCounter = Counter.builder("order.created.total")
            .tag("source", "web")  // 标签维度
            .description("Total number of orders created")
            .register(registry);
    }

    public void createOrder() {
        orderCounter.increment(); // 计数加一
    }

    // 方法耗时指标（通过注解自动生成）
    @Timed(value = "order.process.time", description = "Time taken to process an order")
    public void processOrder() {
        // 业务逻辑
    }
}
```


### **四、Prometheus 配置与数据采集**
#### 1. 编写 Prometheus 配置文件（`prometheus.yml`）
```yaml
global:
  scrape_interval: 15s  # 拉取间隔
  evaluation_interval: 15s # 规则评估间隔

scrape_configs:
  - job_name: "spring-boot-app"
    static_configs:
      - targets: ["localhost:8080"]  # Spring Boot 应用地址
        labels:
          env: dev  # 自定义标签
```

#### 2. 启动 Prometheus
```bash
# 假设配置文件路径为 ./prometheus.yml
./prometheus --config.file=prometheus.yml
```

#### 3. 查看指标
访问 `http://localhost:9090`，在 Prometheus 控制台输入查询语句，例如：
- 查看 JVM 堆内存使用量：`jvm_memory_used_bytes{area="heap"}`
- 查看 HTTP 请求平均耗时：`avg(http_server_requests_seconds_sum{uri="/**"}) / avg(http_server_requests_seconds_count{uri="/**"})`


### **五、扩展与优化**
#### 1. 指标过滤与脱敏
- **排除敏感指标**：在配置中通过 `management.metrics.exporter.prometheus.filter` 排除特定指标：
  ```yaml
  management:
    metrics:
      export:
        prometheus:
          metric-names: "!jvm.gc.*,http.*"  # 排除 JVM GC 指标，包含 HTTP 指标
  ```

#### 2. 生产环境配置建议
- **限制 Actuator 端点访问**：通过安全框架（如 Spring Security）控制 `/actuator` 路径的访问权限。
- **配置指标命名规范**：使用统一的命名前缀（如 `app.myapp.`）避免指标冲突。
- **启用指标聚合**：对于分布式系统，可通过 **Prometheus Gateway** 或 **Thanos** 实现跨实例指标聚合。

#### 3. 与 Grafana 集成可视化
- 安装 Grafana 后，添加 Prometheus 数据源（地址 `http://prometheus-server:9090`）。
- 使用预定义的仪表盘模板（如 [Spring Boot 官方模板](https://grafana.com/grafana/dashboards/6789)）展示指标。


### **六、常见问题**
1. **指标未生效**  
   - 检查依赖是否正确引入（尤其是 `micrometer-registry-prometheus`）。  
   - 确认 Actuator 端点是否启用，路径是否正确（如 `/actuator/prometheus`）。

2. **Prometheus 无法拉取数据**  
   - 确保应用端口（默认 8080）可被 Prometheus 服务器访问。  
   - 检查 Prometheus 配置中的 `targets` 是否正确，是否包含端口号。

3. **自定义指标不显示**  
   - 确认 `MeterRegistry` 已正确注入，指标名称和标签是否拼写正确。  
   - 触发指标关联的业务逻辑，确保指标有数据更新。


通过以上步骤，可快速在 Spring Boot 应用中集成 Prometheus 监控体系，实现对应用性能和运行状态的实时观测。进一步可结合 Alertmanager 实现告警功能，或通过 Grafana 构建定制化监控面板。
