FROM openjdk:17-alpine AS builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar product.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "product.jar"]