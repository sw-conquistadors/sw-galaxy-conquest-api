FROM openjdk:8-jdk-alpine

# Copy the JAR from the target folder into the container
COPY /target/SwGcApi-0.0.1-SNAPSHOT.jar SwGcApi-0.0.1-SNAPSHOT.jar 

ENTRYPOINT ["java", "-jar", "/SwGcApi-0.0.1-SNAPSHOT.jar"]
