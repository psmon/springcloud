FROM openjdk:8-jre-alpine
MAINTAINER wiki.webnori.com
ENV APP_FILE edge-service-0.0.1-SNAPSHOT.jar
ENV APP_HOME /usr/apps
COPY target/$APP_FILE $APP_HOME/
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $APP_FILE"]
EXPOSE 8888