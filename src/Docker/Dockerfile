FROM openjdk:8
ADD api-minerar-twitter.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container","-DDATABASE_HOST=db","-DDATABASE_PORT=3306","-DDATABASE_NAME=twitter","-DDATABASE_USER=twitter","-DDATABASE_PASSWORD=twitter","-jar","app.jar"]