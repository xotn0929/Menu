FROM adoptopenjdk/openjdk11:latest
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} menu.jar
ENTRYPOINT ["java","-jar","/menu.jar"]