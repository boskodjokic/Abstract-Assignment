FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD target/abstract-app-docker.jar abstract-app-docker.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/abstract-app-docker.jar"]