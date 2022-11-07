FROM openjdk:11
MAINTAINER Ben Araar Mortadha <mortadha.benaraar@esprit.tn>


COPY ./target/tpAchatProject-1.0.jar tpAchatProject-1.0.jar

EXPOSE 8080

CMD ["java", "-jar", "/tpAchatProject-1.0.jar"]