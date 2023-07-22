## password

http://localhost:8090/oauth/token?username=user_1&password=12345678&grant_type=password&scope=select&client_id=client_2&client_secret=123456

等同于：

http://client_2:123456@localhost:8090/oauth/token?username=user_1&password=123456&grant_type=password&scope=select

```json
{
    "access_token": "3df8a157-a3eb-42f7-aa08-a79dccdc3fc8",
    "token_type": "bearer",
    "refresh_token": "8d10466a-47ef-4bdd-9169-a4676f23751d",
    "expires_in": 43107,
    "scope": "select"
}
```

http://localhost:8090/order/1?access_token=3df8a157-a3eb-42f7-aa08-a79dccdc3fc8

## refresh_token

http://localhost:8090/oauth/token?grant_type=refresh_token&refresh_token=8d10466a-47ef-4bdd-9169-a4676f23751d&client_id=client_2&client_secret=123456

## client_credentials

http://localhost:8090/oauth/token?grant_type=client_credentials&scope=select&client_id=client_1&client_secret=123456

```json
{
    "access_token": "ad75889d-0f13-4a97-abb9-86d24011ed94",
    "token_type": "bearer",
    "expires_in": 43145,
    "scope": "select"
}
```

http://localhost:8090/order/1?access_token=ad75889d-0f13-4a97-abb9-86d24011ed94

## grant_type

spring-oauth2-tests-jpa .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")

"authorization_code"
"password",
"client_credentials",
"implicit",
"refresh_token"

## 升级异常处理

升级Spring Boot版本报错： stream classdesc serialVersionUID = 500, local class serialVersionUID = 510
清理redis缓存:flushdb （本机测试）

org.springframework.data.redis.serializer.SerializationException: Cannot deserialize;
nested exception is org.springframework.core.serializer.support.SerializationFailedException: Failed to deserialize payload.
Is the byte array a result of corresponding serialization for DefaultDeserializer?;
nested exception is java.io.InvalidClassException: org.springframework.security.core.authority.SimpleGrantedAuthority;
local class incompatible: stream classdesc serialVersionUID = 500, local class serialVersionUID = 510
at org.springframework.data.redis.serializer.JdkSerializationRedisSerializer.deserialize(JdkSerializationRedisSerializer.java:
84) ~[spring-data-redis-2.1.9.RELEASE.jar:2.1.9.RELEASE]
