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
