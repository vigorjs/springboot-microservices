FROM openjdk:17-alpine AS builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-alpine
RUN apk add --no-cache curl
WORKDIR /app
COPY --from=builder /app/target/*.jar discovery.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "discovery.jar"]