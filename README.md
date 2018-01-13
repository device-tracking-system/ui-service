# Devices Tracking System - UI Service
The UI Service serves the client web application for device tracking.

## Project status
[![Build Status](https://travis-ci.org/device-tracking-system/ui-service.svg?branch=master)](https://travis-ci.org/device-tracking-system/ui-service)
[![codebeat badge](https://codebeat.co/badges/7e4d994a-2fcd-468a-8e6e-7cb0bee3bd08)](https://codebeat.co/projects/github-com-device-tracking-system-ui-service-master)

## Prerequisites
You need to have the following tools installed and configured:
  - Java SE 1.8+
  - Maven 3.0+
  - MongoDB 3.0+
  - RabbitMQ Server 3.0+

## Installation and Commissioning
In order to run the UI service, follow these steps:
  1. Run the [Configuration Server](https://github.com/device-tracking-system/configuration-server).
  2. Run the [Service Discovery](https://github.com/device-tracking-system/service-discovery).
  3. Clone the latest production version of this repository from the `master` branch.
  4. Navigate to the cloned repository and install all dependencies by typing:
```
mvn install
``` 
  5. Run the MongoDB Server by typing:
```
mongod --dbpath [path to the directory containing database files]
```
or using the official Docker image:
```
docker run -p 27017:27017 mongo
```
  6. Run the RabbitMQ Message Broker by typing:
```
rabbitmq-server
```
or using the official Docker image:
```
docker run -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```
  7. Run the built `*.jar` file passing the location of configuration files by typing:
```
java -jar target/ui-service-1.0-SNAPSHOT.jar --spring.config.location=classpath:pl/edu/agh/iet/dts/ui/
```

## Building the Docker image
When the `*.jar` file is successfully built, a Docker image for the production environment may be created by applying
following steps:
  1. Enter the root directory of this repository.
  2. Build the Docker image by typing:
```
docker build . -t ui-service
```
  3. In order to run the image, type:
```
docker run -p 8081:8081 -p 44351:44321 -p 44353:44323 -e CONFIGURATION_SERVER_IP=[CONFIGURATION SERVER HOST IP ADDRESS] -e EUREKA_IP=[EUREKA HOST IP ADDRESS] -e ZIPKIN_IP=[ZIPKIN HOST IP ADDRESS] -e MONGODB_IP=[MONGODB HOST IP ADDRESS] -e RABBITMQ_IP=[RABBITMQ HOST IP ADDRESS] -t ui-service
```
Please note that this docker container uses the Performance Co-Pilot (PCP) tool to gather data for system monitoring
metrics. These values are accessed via the `44351` and `44353` ports. In order to visualize performance of this
microservice, please enter the `[CONTAINER IP ADDRESS]:44353` value in the `Hostname` field placed in the Netflix Vector
dashboard.

## Testing
In order to test the application locally, run the built `*.jar` file by typing:
```
java -jar target/ui-service-1.0-SNAPSHOT.jar --spring.profiles.active=test --spring.config.location=classpath:pl/edu/agh/iet/dts/ui/
```
and then execute specific tests.

## Debugging
In order to turn on debug logs for classes located in the `pl.edu.agh.iet.dts.*` package within this repository, please 
activate the `debug`  profile by setting the `--spring.profiles.active=[OTHER PROFILES],debug` flag and adding the 
`--debug` flag.
