FROM gradle:jdk17-alpine

COPY . /app

WORKDIR /app

RUN ./gradlew build 