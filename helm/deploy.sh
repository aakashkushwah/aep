#!/bin/bash

# Start Minikube if not running
#minikube status || minikube start
#eval $(minikube docker-env)

docker pull akushwah/acctmgmt-service:latest
docker pull akushwah/alert-service:latest
docker pull akushwah/notification-service:latest

# Build and push images
#./scripts/build-and-push.sh

pwd

# Deploy with Helm
echo "Deploying microservices to Minikube..."
#helm upgrade --install aep-app .
helm upgrade --install acctmgmt-service ./acctmgmt-service
helm upgrade --install alert-service ./alert-service
helm upgrade --install notification-service ./notification-service


# Wait for pods to be ready
#echo "Waiting for pods to be ready..."
#kubectl wait --for=condition=ready pod -l app.kubernetes.io/name=acctmgmt-service --timeout=120s
#kubectl wait --for=condition=ready pod -l app.kubernetes.io/name=alert-service --timeout=120s
#kubectl wait --for=condition=ready pod -l app.kubernetes.io/name=notification-service --timeout=120s

# Print services information
echo "Deployment complete!"
echo "Access services using:"
echo "  Account Management: $(minikube service acctmgmt-service --url)"
echo "  Notification: $(minikube service notification-service --url)"
echo "  Alert: $(minikube service alert-service --url)"