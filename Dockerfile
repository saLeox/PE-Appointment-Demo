# run to generate multi stage 
FROM openjdk:8-jre-alpine as builder
WORKDIR application
ONBUILD COPY target/*.jar application.jar
ONBUILD RUN java -Djarmode=layertools -jar application.jar extract

# extract
FROM openjdk:8-jre-alpine
WORKDIR application
VOLUME /tmp
ONBUILD COPY --from=builder application/dependencies/ ./
ONBUILD COPY --from=builder application/snapshot-dependencies/ ./
ONBUILD COPY --from=builder application/spring-boot-loader/ ./
ONBUILD COPY --from=builder application/application/ ./
EXPOSE 8081
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]