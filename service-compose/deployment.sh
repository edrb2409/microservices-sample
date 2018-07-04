#!/bin/bash

echo "Dockerizing customer-service"
cd ../customer-service && ./mvnw install dockerfile:build

echo "Dockerizing order-service"
cd ../order-service && ./mvnw install dockerfile:build

cd ../service-compose && docker-compose up -d