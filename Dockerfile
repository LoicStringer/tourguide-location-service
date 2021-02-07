FROM openjdk:8-jre-alpine 
COPY build/libs/*.jar tourguide-location-service.jar
EXPOSE 9004
ENTRYPOINT ["java", "-jar", "tourguide-location-service.jar"]