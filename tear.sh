#!/bin/bash
helm uninstall acctmgmt-service
helm uninstall alert-service
helm uninstall notification-service

#kubectl delete ingress services-ingress

