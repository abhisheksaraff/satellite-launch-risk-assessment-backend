FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /targe/demo-0.0.1-SNAPSHOT/jar satellite-launch-risk-assessment-backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","satellite-launch-risk-assessment-backend.jar"]