# mvn build
FROM maven:3.5.3-jdk-8-alpine AS build  
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app
# build all dependencies
RUN mvn dependency:go-offline -B --fail-never -v /opt/maven/.m2:/root/.m2
RUN mvn -f /usr/src/app/pom.xml clean package -v /opt/maven/.m2:/root/.m2

# run to generate multi stage 
FROM openjdk:8-jre-alpine as builder
WORKDIR application
COPY --from=build /usr/src/app/target/*.jar application.jar
RUN java -Djarmode=layertools -jar application.jar extract

# extract
FROM openjdk:8-jre-alpine
WORKDIR application
VOLUME /tmp
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/application/ ./
EXPOSE 8081
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]