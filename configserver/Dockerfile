FROM openjdk:17-jdk-slim

MAINTAINER gabriel.leone@usp.br

# Install the necessary packages
RUN apt-get update && apt-get install -y \
    curl

COPY target/configserver-0.0.1-SNAPSHOT.jar configserver-0.0.1-SNAPSHOT.jar

# Expose the port the app runs on

ENTRYPOINT ["java","-jar","configserver-0.0.1-SNAPSHOT.jar"]