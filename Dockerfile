#FROM java:8
#VOLUME /tmp
#ADD ./target/microservicecloud.jar ./provider_sample.jar
#RUN bash -c 'touch ./provider_sample.jar'
#EXPOSE 8001
#ENTRYPOINT ["java", "-jar", "./provider_sample.jar"]

FROM maven:3.5-jdk-8 AS build  
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package

FROM gcr.io/distroless/java  
VOLUME /tmp
COPY --from=build /usr/src/app/target/microservicecloud.jar /usr/app/gk1-provider-sample-8001-1.0.0-SNAPSHOT.jar  
EXPOSE 8001 
ENTRYPOINT ["java","-jar","/usr/app/gk1-provider-sample-8001-1.0.0-SNAPSHOT.jar"]
