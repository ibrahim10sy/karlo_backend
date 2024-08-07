FROM openjdk:17
ADD target/demo-0.0.1-SNAPSHOT.jar app1.jar
COPY target/spring-rest.jar spring-basic.jar
EXPOSE 9000
ENTRYPOINT [ "java", "-jar","app1.jar" ]