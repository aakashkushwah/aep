#!/bin/bash
helm upgrade --install acctmgmt-service ./helm/acctmgmt-service
helm upgrade --install alert-service ./helm/alert-service
helm upgrade --install notification-service ./helm/notification-service
