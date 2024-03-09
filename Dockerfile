FROM openjdk:21
ADD target/messaging-application-0.0.1-SNAPSHOT.jar messaging-application.jar
ENTRYPOINT ["java", "-jar", "messaging-application.jar"]