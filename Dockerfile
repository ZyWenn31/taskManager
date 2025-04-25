FROM ubuntu:latest

RUN adduser --system spring-boot && RUN addgroup --system spring-boot && RUN adduser spring-boot spring-boot

USER spring-boot

WORKDIR /app
COPY target/taskManager.jar ./application.jar

ENTRYPOINT ["java", "-jar", "target/application.jar"]