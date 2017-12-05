# Devices Tracking System - UI Service
The UI Service serves the client web application for device tracking.

## Project status
[![Build Status](https://travis-ci.org/device-tracking-system/ui-service.svg?branch=master)](https://travis-ci.org/device-tracking-system/ui-service)
[![codebeat badge](https://codebeat.co/badges/7e4d994a-2fcd-468a-8e6e-7cb0bee3bd08)](https://codebeat.co/projects/github-com-device-tracking-system-ui-service-master)

## Prerequisites
You need to have the following tools installed and configured:
  - Java SE 1.8+
  - Maven 3.0+

## Installation and Commissioning
In order to run the UI service, follow these steps:
  1. Run the [Configuration Server](https://github.com/device-tracking-system/configuration-server).
  2. Run the [Service Discovery](https://github.com/device-tracking-system/service-discovery).
  3. Clone the latest production version of this repository from the `master` branch.
  4. Navigate to the cloned repository and install all dependencies by typing:
```
mvn install
``` 
  5. Run the built `*.jar` file passing the location of configuration files by typing:
```
java -jar target/ui-service-1.0-SNAPSHOT.jar --spring.config.location=classpath:pl/edu/agh/iet/dts/ui/
```
