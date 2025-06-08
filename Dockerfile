# Use OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file into the container
COPY target/SuperMarketSystem-Christmas.jar app.jar

# Command to run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
