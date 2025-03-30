FROM openjdk:21-oracle
EXPOSE 8080
COPY target/hassan-0.0.1-SNAPSHOT.jar hassan-0.0.1-SNAPSHOT.jar
COPY src/main/resources/application.properties application.properties
ENTRYPOINT ["java","-jar","/hassan-0.0.1-SNAPSHOT.jar"]

