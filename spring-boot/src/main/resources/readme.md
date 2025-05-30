### 自定义DateTimeEndPoint

#### 1：用来显示监控指标
http://localhost:9000/actuator/datetime 


#### 2：动态更改监控指标
http://localhost:9000/actuator/datetime
```json
{
  "format": "日期：yyyy-MM-dd 时间：HH:mm:ss"
}
```
#### 3:开关
```shell
management.endpoint.datetime.enabled=true
```


---

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
-ext "SAN=DNS:localhost,DNS:127.0.0.1,IP:192.168.1.100" \  # 支持多个域名/IP
-storepass your_keystore_password \
-keypass your_key_password
```

```shell
  keytool -genkeypair -alias fairy-vip-cert -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -validity 365 -dname "CN=192.168.0.110, OU=fairy-dev, O=fairy-vip, L=zhengzhou, ST=henan, C=CN" -ext "SAN=IP:192.168.0.110,DNS:localhost" -storepass 'fairy-vip-pass' -keypass 'fairy-vip-pass'
```

### **导入证书到系统信任存储**
#### **Windows**
1. 导出证书为 CRT 格式：
   ```bash
     keytool -export -alias fairy-vip-cert -keystore keystore.p12 -rfc -file localip.crt
   ```

2. 双击 `localip.crt` 文件
3. 点击"安装证书"
4. 选择"本地计算机"
5. 存储位置选择"受信任的根证书颁发机构"
6. 完成导入后重启浏览器

### **验证证书密码正确性**
可以通过以下命令验证证书密码是否正确：

```bash
  keytool -list -v -keystore keystore.p12 -storetype PKCS12
```
