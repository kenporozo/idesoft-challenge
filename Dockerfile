FROM openjdk:17
ADD ./target/idesofts-challenge-0.0.1-SNAPSHOT.jar /usr/share/app.jar
ENTRYPOINT ["java", "-jar", "/usr/share/app.jar"]