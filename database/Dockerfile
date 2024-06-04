# We choose exact tag (not 'latest'), to be sure that new version wont break creating image
FROM mysql:8.0.0

ENV MYSQL_ROOT_PASSWORD secret

COPY . /
ADD create-database.sql /docker-entrypoint-initdb.d

EXPOSE 3308