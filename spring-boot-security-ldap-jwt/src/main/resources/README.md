### 0. 原始文档

#### Example Spring WebFlux Application with LDAP+JWT Security【[源代码](https://github.com/thmshmm/spring-security-ldap-jwt-example)】

##### Demo using an embedded LDAP server to test multiple authentication managers.

User / Password (Groups):
* user1 / user1 (users)
* admin / admin (admins, users)

##### LDAP Groups are mapped into the JWT token as roles which are used to authorize the endpoints.

API:
* /auth/login (issues JWT tokens)
* /api/hello (accessible as admin and user1)
* /api/hello-admin (accessible only as admin)
----


要测试这个仓库的功能，你可以按照以下步骤进行：

### 1. 克隆仓库
首先，将该仓库克隆到本地机器上。在命令行中执行以下命令：
```bash
git clone <仓库地址>
cd spring-security-ldap-jwt-example
```

### 2. 构建项目
确保你已经安装了Maven，然后使用Maven构建项目。在项目根目录下执行以下命令：
```bash
mvn clean install
```
这个命令会下载所有依赖，并编译项目。

### 3. 运行项目
使用Maven插件启动Spring Boot应用程序：
```bash
mvn spring-boot:run
```
或者，你也可以将项目导入到IDE（如IntelliJ IDEA或Eclipse）中，然后直接从IDE中运行`Application.java`类的`main`方法。

### 4. 测试API

#### 4.1 登录获取JWT令牌
使用`curl`或Postman等工具向`/auth/login`端点发送请求以获取JWT令牌。以下是使用`curl`的示例：

```bash
curl -X GET http://localhost:8080/auth/login -u admin:admin
```
其中，`-u`选项用于提供用户名和密码。如果登录成功，你将收到一个包含JWT令牌的JSON响应。

#### 4.2 测试普通用户可访问的端点
使用获取到的JWT令牌访问`/api/hello`端点。示例如下：
```bash
curl -X GET http://localhost:8080/api/hello -H "Authorization: Bearer <JWT令牌>"
```
将`<JWT令牌>`替换为你实际获取到的令牌。如果一切正常，你应该能看到响应内容。

#### 4.3 测试管理员可访问的端点
使用管理员的JWT令牌访问`/api/hello-admin`端点：
```bash
curl -X GET http://localhost:8080/api/hello-admin -H "Authorization: Bearer <JWT令牌>"
```
如果使用的是管理员令牌，你应该能正常访问该端点；如果使用普通用户的令牌，应该会收到403禁止访问的错误。

### 5. 测试不同用户
你可以使用不同的用户（如`user1`）进行登录，并测试相应的访问权限。例如：
```bash
curl -X GET http://localhost:8080/auth/login -u user1:user1
```
然后使用获取到的令牌访问不同的端点，验证权限控制是否正常工作。

### 6. 测试JWT令牌续期（待实现功能）
根据`README.md`中的`TODO`列表，JWT令牌续期功能还未实现。如果你想要实现这个功能，可以在项目中添加相应的代码，例如添加一个新的API端点用于续期令牌，然后测试该端点的功能。
