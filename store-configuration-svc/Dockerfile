FROM adoptopenjdk/openjdk14:alpine
VOLUME /tmp
ARG GIT_COMMIT
LABEL GitCommit=${GIT_COMMIT}

ADD target/store-configuration-svc-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
