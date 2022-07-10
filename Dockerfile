FROM openjdk:11-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} menu.jar
ENTRYPOINT ["java","-jar","/menu.jar"]