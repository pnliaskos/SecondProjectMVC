FROM openjdk:17
LABEL maintainer="asfyld"
ADD target/SecondProjectMVC-0.0.1-SNAPSHOT.jar docker-mvc.jar
ENTRYPOINT ["java", "-jar", "docker-mvc.jar"]