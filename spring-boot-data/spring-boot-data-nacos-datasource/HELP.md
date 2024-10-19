# Getting Started

### 1: 前提

```
目前，nacos-config-spring-boot-starter最高版本:0.2.12
支持到Spring Boot:v2.7.18
```

### 2：nacos配置

`Data ID`

```
mysql.properties
```

`配置内容`

```
server.port=9009
#
useLocalCache=false
#
logging.level.org.springframework=info
#
################### DataSource Configuration ##########################
spring.datasource.url=jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=${FAIRY_MYSQL_PWD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#spring.datasource.initialize=true
################### Hibernate Configuration ##########################
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

