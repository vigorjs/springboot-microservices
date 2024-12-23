FROM openjdk:17-alpine AS builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-alpine
RUN apk add --no-cache curl
WORKDIR /app
COPY --from=builder /app/target/*.jar configserver.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "configserver.jar"]