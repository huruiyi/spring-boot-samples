# ActiveMQ 5.19.8 启动指南

## 环境信息

| 项目 | 说明 |
|---|---|
| ActiveMQ 版本 | 5.19.8 (Classic) |
| 安装路径 | `C:\App\Ac\apache-activemq-5.19.8` |
| JDK | JDK 17 (`C:\App\jdk\jdk-17`) |
| 客户端版本 | 5.16.7（Spring Boot 2.7.18 内置管理） |

## 端口

| 端口 | 协议 | 说明 |
|---|---|---|
| 61616 | OpenWire (TCP) | JMS 客户端连接，JMX 管理 |
| 61613 | STOMP | STOMP 协议客户端连接 |
| 8161 | HTTP | Web 管理控制台（待完善 jetty.xml） |

## 启动前准备

### 1. 检查缺失文件
原始压缩包 `apache-activemq-5.19.8-bin.zip` 解压后 `conf/` 目录可能缺失默认配置文件，需补充以下文件：

| 文件 | 路径 | 说明 |
|---|---|---|
| `README.txt` | `C:\App\Ac\apache-activemq-5.19.8\` | 启动脚本校验标记文件（空文件即可） |
| `activemq.xml` | `C:\App\Ac\apache-activemq-5.19.8\conf\` | Broker 核心配置文件 |
| `jetty.xml` | `C:\App\Ac\apache-activemq-5.19.8\conf\` | 内嵌 Jetty Web 容器配置 |
| `credentials.properties` | `C:\App\Ac\apache-activemq-5.19.8\conf\` | 凭证配置（空文件即可） |

### 2. `activemq.xml` 核心配置
```xml
<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://activemq.apache.org/schema/core
    http://activemq.apache.org/schema/core/activemq-core.xsd">

    <broker xmlns="http://activemq.apache.org/schema/core"
            brokerName="localhost"
            dataDirectory="${activemq.data}">

        <destinationPolicy>
            <policyMap>
              <policyEntries>
                <policyEntry topic=">">
                    <pendingMessageLimitStrategy>
                        <constantPendingMessageLimitStrategy limit="1000"/>
                    </pendingMessageLimitStrategy>
                </policyEntry>
              </policyEntries>
            </policyMap>
        </destinationPolicy>

        <managementContext>
            <managementContext createConnector="false"/>
        </managementContext>

        <persistenceAdapter>
            <kahaDB directory="${activemq.data}/kahadb"/>
        </persistenceAdapter>

        <systemUsage>
            <systemUsage>
                <memoryUsage>
                    <memoryUsage percentOfJvmHeap="70"/>
                </memoryUsage>
                <storeUsage>
                    <storeUsage limit="100 gb"/>
                </storeUsage>
                <tempUsage>
                    <tempUsage limit="50 gb"/>
                </tempUsage>
            </systemUsage>
        </systemUsage>

        <transportConnectors>
            <transportConnector name="openwire"
                uri="tcp://0.0.0.0:61616?maximumConnections=1000"/>
            <transportConnector name="stomp"
                uri="stomp://0.0.0.0:61613?maximumConnections=1000"/>
        </transportConnectors>
    </broker>

    <import resource="jetty.xml"/>
</beans>
```

## 启动命令

### 方式一：批处理脚本（推荐）

创建 `C:\App\Ac\apache-activemq-5.19.8\bin\start-test.bat`：

```batch
set JAVA_HOME=C:\App\jdk\jdk-17
set ACTIVEMQ_HOME=C:\App\Ac\apache-activemq-5.19.8
call C:\App\Ac\apache-activemq-5.19.8\bin\activemq.bat start
```

执行：
```powershell
cmd /c C:\App\Ac\apache-activemq-5.19.8\bin\start-test.bat
```

### 方式二：CMD 直接启动

```cmd
set JAVA_HOME=C:\App\jdk\jdk-17
set ACTIVEMQ_HOME=C:\App\Ac\apache-activemq-5.19.8
cd /d C:\App\Ac\apache-activemq-5.19.8\bin
activemq.bat start
```

> **注意**：PowerShell 的 `cmd /c "set VAR=val && command"` 无法正确传递环境变量给批处理脚本，
> 必须用 `.bat` 文件包裹或直接使用 CMD 命令行。

## 管理命令

```cmd
# 启动
activemq.bat start

# 停止
activemq.bat stop

# 查看状态
activemq.bat status

# 控制台模式（前台运行，Ctrl+C 停止）
activemq.bat console
```

## 验证启动

```powershell
# 检查 Java 进程
Get-Process -Name "java*" | Select Id, ProcessName

# 检查端口
Test-NetConnection -ComputerName localhost -Port 61616
```

## Spring Boot 客户端配置

```properties
# ActiveMQ 连接
spring.activemq.broker-url=tcp://localhost:61616
# 内嵌 broker 作为外部不可用时的回退
spring.activemq.in-memory=true
# 信任所有包（对象消息序列化）
spring.activemq.packages.trust-all=true
```

## 兼容性

ActiveMQ Classic 5.x 之间通过 **OpenWire 协议**通信，向后兼容：
- 客户端 5.16.7 ↔ Broker 5.19.8：启动时自动协商协议版本，完全兼容
- 如需对齐版本，可在 POM 中覆盖 `<activemq.version>5.19.8</activemq.version>`
