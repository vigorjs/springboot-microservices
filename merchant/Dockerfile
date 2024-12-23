FROM openjdk:17-alpine AS builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar merchant.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "merchant.jar"]