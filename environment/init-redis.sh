#!/bin/bash

# Populate Redis with initial data
redis-cli -h localhost -p 6379 SET key1 "value1"
redis-cli -h localhost -p 6379 SET key2 "value2"
redis-cli -h localhost -p 6379 HSET user:1001 name "John Doe" age 30
redis-cli -h localhost -p 6379 HSET user:1002 name "Jane Smith" age 25

echo "Redis cache initialized with sample data."