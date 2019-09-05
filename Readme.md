#UtdallasScrape.git

This is a tool which scrapes data from https://www.utdallas.edu/services/transit/garages/

## Prerequisites
Java8
Maven

## Steps to run the tool

1) Build the maven project using "mvn clean compile assembly:single".

2) Now run the command "java -cp target/UtdallasScrape-1.0-SNAPSHOT-jar-with-dependencies.jar com.web.scrape.ParkingScrape numberOfMinutes" from the root folder

numberOfMinutes is the numerical value after which the spaces details are fetched again
