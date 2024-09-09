FROM openjdk:11-jre-slim
  #COPY wait-for-kafka.sh /wait-for-kafka.sh
COPY target/transaction-service-app-1.0-SNAPSHOT.jar transaction-service-app.jar
ENTRYPOINT ["java", "-jar", "/transaction-service-app.jar"]