### 0：前提

打包应用，拷贝Dockerfile和打包后的jar文件到一个新的目录，执行构建镜像的命令

### 1：创建Dockerfile

```dockerfile
FROM openjdk:8-jdk-alpine
COPY *.jar /app.jar
CMD ["--server.port=22336"]
EXPOSE 20000
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
```

### 2：构建镜像

```shell
docker build -t huruiyi/spring-boot-hello-world:v1 .
```



### 3：推送镜像
```shell
docker push huruiyi/spring-boot-hello-world:v1
```



### 4：拉取镜像

```
docker pull huruiyi/spring-boot-hello-world:v1
```

