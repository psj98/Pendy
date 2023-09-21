FROM adoptopenjdk/openjdk11
EXPOSE 8081
ARG JAR_FILE=build/libs/namani-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]