FROM postgres:14.2-alpine

ENV TZ=Europe/Moscow
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ADD ddl/*.sql /docker-entrypoint-initdb.d/
ADD dml/*.sql /docker-entrypoint-initdb.d/
