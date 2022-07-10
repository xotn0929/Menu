FROM adoptopenjdk/openjdk11:latest
#RUN mkdir /build/libs
#ARG JAR_FILE=/build/libs/*.jar
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} menu.jar
ENTRYPOINT ["java","-jar","/menu.jar"]