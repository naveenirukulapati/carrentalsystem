FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/CarRentalSystem-0.0.1-SNAPSHOT.jar CarRentalSystem-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar","/CarRentalSystem-0.0.1-SNAPSHOT.jar"]
EXPOSE 8888