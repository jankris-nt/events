#!/bin/sh
# mvn clean install
# mvn clean package

# mvn install
# mvn clean
# mvn -e package 

# mvn spring-boot:run -Dagentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000
# java -jar ./target/apse-0.0.1-SNAPSHOT.jar
mvn spring-boot:run
