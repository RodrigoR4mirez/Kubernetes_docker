FROM maven:3.6.3-jdk-11-slim AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:11.0.6-jdk
LABEL maintainer="developer@company.com"
WORKDIR /workspace
RUN ls -la /workspace
COPY --from=builder /app/target/application*.jar app.jar
ENTRYPOINT ["java", "-jar", "/workspace/app.jar"]
#ENTRYPOINT exec java -jar /workspace/app.jar
