## Messaging Application 

Application to send messages/text from one user to another. Every user is identified by a unique ID which is the user provided nickname.   

### Requirements

For building and running the application you need:

- [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven](https://maven.apache.org)
- [PostgreSQL](https://www.postgresql.org/download/)
- [Kafka](https://kafka.apache.org/downloads)
- [Docker Engine](https://docs.docker.com/engine/install/)

### Running the application

Step 1: Git Clone https://github.com/merylleona/project-repo

Step 2: For DB and Kafka setup, from root directory of project execute docker-compose.yml

```shell
docker-compose up -d --build
```

This will create,

* PostgreSQL DB container
* Kafka container

Step 3: Execute the Spring Boot application on your local machine

* Run the `main` method in `com.app.messagingapplication` class from your IDE

or

* Use the Spring Boot Maven plugin

```shell
mvn spring-boot:run
```

Once the application is up and running, explore messaging application APIs through Swagger 

http://localhost:8080/swagger-ui/index.html#/

### Requirements Implemented

1. REST webservice HTTP API implementation for
   * Creating new account using (unique) nickname
   * Sending message to another user identified by his/her ID (nickname)
   * Viewing all messages that I received
   * Viewing all messages that I sent
   * Viewing all messages received from a particular user
2. All data/messages persisted in PostgreSQL
3. Sending message puts a message on Kafka queue
4. Unit tests written using Mockito framework for Test-Driven Development (TDD)
5. Flyway integration for DB script executions

### Improvements to existing application

* Implement API security using OAuth
* Implement audit report generation feature