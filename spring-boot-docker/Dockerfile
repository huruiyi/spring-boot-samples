FROM openjdk:8-jdk-alpine
COPY *.jar /app.jar
CMD ["--server.port=22336"]
EXPOSE 20000
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]