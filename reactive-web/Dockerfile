FROM openjdk:8-jre-alpine
COPY ./target/reactive-web.jar /usr/src/reactive-web/
WORKDIR /usr/src/reactive-web
CMD ["java", "-jar", "reactive-web.jar"]