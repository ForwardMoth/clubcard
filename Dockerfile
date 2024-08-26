FROM eclipse-temurin:21-jre-alpine
WORKDIR .
COPY ./build/clubcard-0.0.1-SNAPSHOT.jar clubcard.jar
ENTRYPOINT ["java","-jar","clubcard.jar"]