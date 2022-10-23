# Getting Started

### 1: 前提
```
目前，nacos-config-spring-boot-starter最高版本0.2.10,只支持到spring-boot:2.3.7.RELEASE
```
### 2：nacos配置
`Data ID`
```
mysql.properties
```
`配置内容`
```
server.port=9009

logging.level.org.springframework=info
################### DataSource Configuration ##########################
# spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=test
spring.datasource.password=test
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#spring.datasource.initialize=true
################### Hibernate Configuration ##########################
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

