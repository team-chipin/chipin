# Build stage
FROM gradle:8.3.0-jdk20-alpine AS BUILD

RUN gradle --version && java -version

WORKDIR /app

# Only copy dependency-related files
COPY build.gradle settings.gradle /app/

# Only download dependencies
# Eat the expected build failure since no source code has been copied yet
RUN gradle clean build --no-daemon > /dev/null 2>&1 || true

# Copy all files
COPY ./ /app/

# Do the actual build
RUN gradle clean build --no-daemon

# Package stage
FROM amazoncorretto:17.0.4-alpine3.16

# Install Nginx
RUN apk update && apk add nginx

# Create directories for the Nginx PID file and logs
RUN mkdir -p /run/nginx && \
    mkdir -p /var/log/nginx

# Copy the built JAR file
ENV JAR_NAME=chipin-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/app/
WORKDIR $APP_HOME
COPY --from=BUILD $APP_HOME .

# Copy Nginx configuration
COPY nginx.conf /etc/nginx/nginx.conf

# Expose ports for Spring Boot and Nginx
EXPOSE 8080 8081

# Start Nginx and the Spring Boot application
CMD nginx; exec java -jar $APP_HOME/build/libs/$JAR_NAME