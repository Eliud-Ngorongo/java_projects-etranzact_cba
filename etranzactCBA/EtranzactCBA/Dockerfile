FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD build/libs/GENERATED_NAME_HERE.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]