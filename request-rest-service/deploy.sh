#!/usr/bin/env bash


mvn clean package -DskipTests

cd target

java -jar request-rest-service-0.0.1-SNAPSHOT.jar

