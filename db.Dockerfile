FROM mysql:latest

ENV MYSQL_DATABASE=library \
    MYSQL_ROOT_USERNAME=root \
    MYSQL_ROOT_PASSWORD=root_root

#COPY schema.sql /docker-entrypoint-initdb.d
