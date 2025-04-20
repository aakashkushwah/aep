#!/bin/bash

# Start Minikube if not running
minikube status || minikube start

# Build and push images
#./scripts/build-and-push.sh

pwd

# Deploy with Helm
echo "Deploying microservices to Minikube..."
helm upgrade --install aep-app .

# Wait for pods to be ready
echo "Waiting for pods to be ready..."
kubectl wait --for=condition=ready pod -l app.kubernetes.io/name=springboot-microservices --timeout=120s

# Print services information
echo "Deployment complete!"
echo "Access services using:"
echo "  Account Management: $(minikube service aep-app-acctmgmt --url)"
echo "  Notification: $(minikube service aep-app-notification --url)"
echo "  Alert: $(minikube service aep-app-alert --url)"