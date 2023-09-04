FROM openjdk:17-oracle

ENV TZ=Europe/Moscow
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ADD cleverBank-1.0-SNAPSHOT.jar /app/
