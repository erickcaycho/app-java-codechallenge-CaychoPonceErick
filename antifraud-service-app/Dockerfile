FROM openjdk:11-jre-slim
#COPY wait-for-kafka.sh /wait-for-kafka.sh
COPY target/antifraud-service-app-1.0-SNAPSHOT.jar antifraud-service-app.jar
ENTRYPOINT ["java", "-jar", "/antifraud-service-app.jar"]