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
  6. Run the RabbitMQ Message Broker by typing:
```
rabbitmq-server
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
docker run -p 8081:8081 -t ui-service
```

## Testing
In order to test the application locally, run the built `*.jar` file by typing:
```
java -jar target/ui-service-1.0-SNAPSHOT.jar --spring.profiles.active=test --spring.config.location=classpath:pl/edu/agh/iet/dts/ui/
```
and then execute specific tests.

## Debugging
In order to turn on debug logs for classes located in the `pl.edu.agh.iet.dts.*` package within this repository, please 
activate the `test`  profile by setting the `--spring.profiles.active=[OTHER PROFILES],test` flag and adding the 
`--debug` flag.
