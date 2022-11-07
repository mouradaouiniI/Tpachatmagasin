FROM openjdk:11
MAINTAINER Outayel Chaouachi <outayel.chaouachi@esprit.tn>


COPY ./target/tpAchatProject-2.0.jar tpAchatProject-2.0.jar

EXPOSE 8080

CMD ["java", "-jar", "/tpAchatProject-2.0.jar"]