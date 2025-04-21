# Use a lightweight OpenJDK image for the runtime environment
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

COPY libs libs/

COPY resources resources/

COPY classes classes/

ENTRYPOINT ["java", "-cp", "/app/resources:/app/classes:/app/libs/*", "com.archexc.acctmgmtservice.AcctmgmtServiceApplication"]

# Copy the compiled executable JAR file into the container
# Replace `acctmgmt-service-1.0.0.jar` with your actual JAR file name
#COPY target/acctmgmt-service-1.0.0.jar app.jar

# Expose the port that the application runs on (default is 8080 for Spring Boot apps)
EXPOSE 8092

# Command to run the JAR file
#ENTRYPOINT ["java", "-jar", "app.jar"]