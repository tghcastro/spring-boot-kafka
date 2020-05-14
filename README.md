# Spring Boot Kafka Usage

Based on:

1. Pluralsight's course [Getting Started with Apache Kafka](https://app.pluralsight.com/library/courses/apache-kafka-getting-started/table-of-contents)
2. Baeldung's article [Intro to Apache Kafka with Spring](https://www.baeldung.com/spring-kafka)

Application's API:

GET <http://localhost:8080/message> (Retrieve all consumed messages)
POST <http://localhost:8080/message?newMessage=mymessage> (Create a message)
POST <http://localhost:8080/message/async?newMessage=mymessageasync> (Create a message assyncrounously)

* Postman collection available in repository

Services Ports:

| Service           | Port              |
| ----------------- |------------------:|
| Zookeeper         | 2181              |
| Kafka Cluster     | 9090, 9091, 9092  |
| Spring Boot App   | 8080              |

## Running

### Kafka installation

```shell_script
brew install kafk
```

### Running services (zookeeper and kafka)

```shell_script
brew services start zookeeper

brew services start kafka
```

### Starting Kafka cluster

```shell_script
kafka-server-start ./cluster-config/server-0.properties
kafka-server-start ./cluster-config/server-1.properties
kafka-server-start ./cluster-config/server-2.properties
```

### Running Spring Boot application

```shell_script
mvn spring-boot:run
```

### Kafka commands

```shell_script
kafka-topics --list --zookeeper localhost:2181

kafka-topics --create --zookeeper localhost:2181 --replication-factor 3 --partitions 1 --topic replicated_topic

kafka-topics --describe --zookeeper localhost:2181 --topic replicated_topic

kafka-console-producer --broker-list localhost:9092 --topic replicated_topic

kafka-console-consumer --bootstrap-server localhost:9092 --topic replicated_topic --from-beginning
```
