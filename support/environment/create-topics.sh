#!/bin/bash

# Create Kafka topics
docker exec kafka kafka-topics.sh --create --topic test-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
docker exec kafka kafka-topics.sh --create --topic notification-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
docker exec kafka kafka-topics.sh --create --topic alert-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
docker exec kafka kafka-topics.sh --create --topic account-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

echo "Kafka topics created successfully."