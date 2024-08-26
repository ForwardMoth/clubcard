FROM eclipse-temurin:21-jre-alpine
WORKDIR .
COPY ./build/clubcard-0.0.1-SNAPSHOT.jar clubcard.jar
RUN apk update && apk add curl
ENTRYPOINT ["java","-jar","clubcard.jar"]