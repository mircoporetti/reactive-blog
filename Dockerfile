FROM openjdk:17

ARG jarFile
ARG dbConnection

ENV JAR_FILE=${jarFile}
ENV DB_CONNECTION=${dbConnection}

RUN export DEBIAN_FRONTEND=noninteractive

ADD ./web-application/target/${jarFile} /app/

ENTRYPOINT [ "sh", "-c", "java -Xmx1g -jar /app/${JAR_FILE} --spring.data.mongodb.uri=${DB_CONNECTION}" ]

EXPOSE 8085