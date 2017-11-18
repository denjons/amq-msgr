#!/usr/bin/env bash


sleep 20

mvn clean package

cd target

java -jar request-agg-service-0.0.1-SNAPSHOT.jar

