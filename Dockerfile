FROM openjdk:17
VOLUME /tmp
EXPOSE 9090
ADD target/XmartApp-0.0.1-SNAPSHOT.jar XmartApp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "XmartApp-0.0.1-SNAPSHOT.jar"]