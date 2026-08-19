# spring-boot 模块说明

## DateTimeEndPoint（自定义 Actuator 端点）

类：`com.example.config.DateTimeEndPoint`  
端点 ID：`datetime`  
路径：`/actuator/datetime`

用于演示自定义 Actuator：`GET` 查询当前时间，`POST` 动态修改时间格式。

### 1. 配置开关

`application-dev.properties`：

```properties
management.endpoint.datetime.enabled=true
management.endpoints.web.exposure.include=*
```

Bean 在 `WebConfig` 中通过 `@ConditionalOnAvailableEndpoint` 注册，开关关闭时不会创建该端点。

### 2. 当前环境访问地址

本模块 `dev` 环境启用了 HTTPS，端口为 **443**（默认 HTTPS 端口，URL 中可省略）：

| 操作 | 方法 | URL |
|------|------|-----|
| 查询时间 | GET | `https://localhost/actuator/datetime` |
| 修改格式 | POST | `https://localhost/actuator/datetime` |

自签名证书需加 `-k`（curl）或 `-SkipCertificateCheck`（PowerShell）。

### 3. 查询（GET / @ReadOperation）

```bash
curl.exe -k https://localhost/actuator/datetime
```

成功响应示例：

```json
{"format":"yyyy-MM-dd HH:mm:ss","time":"2026-08-19 15:31:45"}
```

### 4. 修改格式（POST / @WriteOperation）

JSON 根字段名必须是 **`format`**，与方法参数名一致：

```java
@WriteOperation
public Map<String, Object> setDateTime(String format) { ... }
```

`format` 需符合 `DateTimeFormatter` 模式，例如：

- `yyyy-MM-dd HH:mm:ss`
- `yyyy年MM月dd日 HH:mm:ss`
- `HH:mm:ss`

#### 4.1 Git Bash / Linux / macOS（bash）

```bash
curl -k -X POST 'https://localhost/actuator/datetime' \
  -H 'Content-Type: application/json' \
  -d '{"format":"yyyy年MM月dd日 HH:mm:ss"}'
```

#### 4.2 Windows PowerShell（推荐）

PowerShell 中 **不要** 使用 bash 风格的 `\"` 转义，否则请求体会变成字面量 `{\"format\":...}`，服务端报：

`JSON parse error: Unexpected character ('\')`

正确写法（单引号包住 JSON，内部用双引号）：

```powershell
curl.exe -k -X POST "https://localhost/actuator/datetime" `
  -H "Content-Type: application/json" `
  -d '{"format":"yyyy年MM月dd日 HH:mm:ss"}'
```

或用文件传 body（最稳妥，避免转义问题）：

```powershell
'{"format":"yyyy年MM月dd日 HH:mm:ss"}' | Out-File -Encoding utf8NoBOM body.json
curl.exe -k -X POST "https://localhost/actuator/datetime" `
  -H "Content-Type: application/json" `
  --data-binary "@body.json"
```

或用 PowerShell 原生：

```powershell
Invoke-RestMethod -SkipCertificateCheck -Method Post `
  -Uri "https://localhost/actuator/datetime" `
  -ContentType "application/json; charset=utf-8" `
  -Body '{"format":"yyyy-MM-dd HH:mm:ss"}'
```

> 注意：PowerShell 里 `curl` 可能是 `Invoke-WebRequest` 的别名，建议始终写 **`curl.exe`**。

#### 4.3 查询参数方式（可选）

POST 的 `format` 也可放在 query；Actuator 写操作仍建议带上 JSON Content-Type：

```powershell
curl.exe -k -X POST "https://localhost/actuator/datetime?format=yyyy-MM-dd" `
  -H "Content-Type: application/json" `
  -d "{}"
```

### 5. 成功 / 失败对照

| 场景 | HTTP | 说明 |
|------|------|------|
| GET 正常 | 200 | 返回 `format` + `time` |
| POST 正常 | 200 | 返回更新后的 `format` + `time` |
| JSON 转义错误（PowerShell 误用 `\"`） | 400 | `Unexpected character ('\')` |
| POST 未带 `Content-Type: application/json` | 415 | Unsupported Media Type |
| `format` 非法模式 | 500 | `非法时间格式: ...` |

### 6. 首页联调

打开 `https://localhost/`，在接口列表中选择：

- `自定义日期端点·查询`（GET）
- `自定义日期端点·改格式`（POST，body 已预填 JSON）

### 7. 实现要点

1. `@Endpoint(id = "datetime")` → 路径 `/actuator/datetime`
2. `@ReadOperation` → GET；`@WriteOperation` → POST
3. 参数从 **JSON 根属性** 或 **query** 映射到方法参数，仅支持简单类型
4. 需保留方法参数名（本项目使用 `spring-boot-starter-parent`，默认开启 `-parameters`）
5. 时间格式使用 `DateTimeFormatter`（线程安全），不要用非线程安全的 `SimpleDateFormat`

---

## SSL 证书（PKCS12）

以下是使用 `keytool` 生成 PKCS12 格式 SSL 证书的完整命令，包含了所有必要的基本信息（口令、域名、组织等）：

```bash
keytool -genkeypair \
-alias mycert \
-keyalg RSA \
-keysize 2048 \
-storetype PKCS12 \
-keystore keystore.p12 \
-validity 365 \
-dname "CN=localhost, OU=部门名称, O=组织名称, L=城市, ST=省份, C=CN" \
-storepass your_keystore_password \
-keypass your_key_password
```

### 参数说明：
1. **基本配置**：
   - `-alias mycert`：证书别名
   - `-keyalg RSA`：使用 RSA 算法
   - `-keysize 2048`：密钥长度 2048 位
   - `-storetype PKCS12`：证书格式为 PKCS12
   - `-keystore keystore.p12`：生成的证书文件名
   - `-validity 365`：证书有效期 365 天

2. **证书主体信息（-dname 参数）**：
   - `CN=localhost`：**必须与访问域名一致**（开发环境可使用 `localhost`）
   - `OU=部门名称`：组织单位
   - `O=组织名称`：组织名称
   - `L=城市`：城市
   - `ST=省份`：省份
   - `C=CN`：国家代码（中国为 `CN`）

3. **密码配置**：
   - `-storepass your_keystore_password`：证书库密码
   - `-keypass your_key_password`：私钥密码（建议与证书库密码一致）


### Spring Boot 配置示例
生成证书后，在 `application.properties` 中添加以下配置：

```properties
server.port=8443
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-type=PKCS12
server.ssl.key-store-password=your_keystore_password
server.ssl.key-alias=mycert
server.ssl.key-password=your_key_password  # 如果与 keystore 密码相同，可省略
```


### 注意事项：
1. **CN 必须匹配域名**：
   - 如果是生产环境，`CN` 应设置为实际访问的域名（如 `www.example.com`）
   - 开发环境可使用 `localhost`，但浏览器仍会显示安全警告

2. **密码安全**：
   - 不要在配置文件中硬编码密码，建议使用环境变量或配置中心管理
   - 生产环境建议使用更复杂的密码

3. **自签名证书限制**：
   - 自签名证书仅适用于开发和测试环境
   - 生产环境需使用受信任的 CA 颁发的证书（如 Let's Encrypt）


如果需要生成包含 SAN（Subject Alternative Name）的证书（支持多个域名或 IP），可以使用以下扩展命令：

```bash
 keytool -genkeypair \
-alias mycert \
-keyalg RSA \
-keysize 2048 \
-storetype PKCS12 \
-keystore keystore.p12 \
-validity 365 \
-dname "CN=localhost, OU=部门名称, O=组织名称, L=城市, ST=省份, C=CN" \
-ext "SAN=DNS:localhost,IP:127.0.0.1,IP:172.22.128.30" \
-storepass your_keystore_password \
-keypass your_key_password
```

```shell
  keytool -genkeypair -alias fairy-vip-cert -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -validity 365 -dname "CN=172.22.128.30, OU=fairy-dev, O=fairy-vip, L=zhengzhou, ST=henan, C=CN" -ext "SAN=DNS:localhost,IP:127.0.0.1,IP:172.22.128.30" -storepass 'fairy-vip-pass' -keypass 'fairy-vip-pass'
```

### 导入证书到系统信任存储
#### Windows
1. 导出证书为 CRT 格式：
   ```bash
     keytool -export -alias fairy-vip-cert -keystore keystore.p12 -rfc -file localip.crt
   ```

2. 双击 `localip.crt` 文件
3. 点击"安装证书"
4. 选择"本地计算机"
5. 存储位置选择"受信任的根证书颁发机构"
6. 完成导入后重启浏览器

### 验证证书密码正确性
可以通过以下命令验证证书密码是否正确：

```bash
  keytool -list -v -keystore keystore.p12 -storetype PKCS12
```
