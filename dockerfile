FROM openjdk:21

LABEL mentainer ="pk356639@gmail.com"

WORKDIR /app

COPY target/BusBooking-0.0.1-SNAPSHOT.jar /usr/local/tomcat/webapps/BusBooking-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","BusBooking-0.0.1-SNAPSHOT.jar"]


