# Stage 1: Build the application
FROM maven:3.8.7-eclipse-temurin-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and any dependencies
COPY pom.xml ./

# Download Maven dependencies (this helps in caching)
RUN mvn dependency:go-offline -B

# Copy the source code into the container
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/*.jar app.jar
COPY ../.env .

# Expose the application's port
EXPOSE 65515

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
