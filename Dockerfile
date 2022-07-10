FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
ADD target/*.jar menu.jar
#COPY ${JAR_FILE} menu.jar
ENTRYPOINT ["java","-jar","/menu.jar"]