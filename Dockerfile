FROM openjdk:17-alpine

ARG JAR_PATH=./build/libs/myToyProject-0.0.1-SNAPSHOT.jar

COPY ${JAR_PATH} app.jar

CMD ["java","-jar","./app.jar"]