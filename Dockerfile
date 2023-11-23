FROM openjdk:17
ADD /target/shopp-0.0.1-SNAPSHOT.jar backend-java.jar
ENTRYPOINT ["java", "-jar", "backend-java.jar"]
