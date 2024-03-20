# Use a imagem do seu software Spring Boot
FROM alpine:3.19

# Instale o curl
RUN apk add --no-cache openjdk17

RUN apk add --no-cache curl

# Copie o arquivo para o container
COPY target/configserver-0.0.1-SNAPSHOT.jar /configserver-0.0.1-SNAPSHOT.jar

# Execute o comando para rodar o seu software
CMD ["java", "-jar", "/configserver-0.0.1-SNAPSHOT.jar"]