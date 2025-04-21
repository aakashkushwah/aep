#!/bin/bash

# Set to use Minikube's Docker daemon
eval $(minikube docker-env)

# Build Docker images
echo "Building Docker images..."
pwd

cd ../notification-service || exit 1
./gradlew dockerPushImage

pwd

cd ../alert-service || exit 1
./gradlew dockerPushImage

pwd

cd ../acctmgmt-service || exit 1
./gradlew dockerPushImage



#docker build -t acctmgmt-service:latest ../acctmgmt-service
#docker build -t notification-service:latest ../notification-service
#docker build -t alert-service:latest ../alert-service

echo "Images built successfully"