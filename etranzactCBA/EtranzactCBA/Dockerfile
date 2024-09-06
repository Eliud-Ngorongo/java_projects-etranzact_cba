FROM gradle:6.8.0-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon -x test

FROM openjdk:24-ea-12-jdk-oraclelinux8

EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/EtranzactCBA-0.1-all.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]