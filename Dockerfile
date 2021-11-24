# pull official base image
FROM ubuntu:20.04

# running dpkg without interactive dialogue
ARG DEBIAN_FRONTEND=noninteractive

# install os dependencies
RUN apt update
RUN apt install -y wget unzip openjdk-11-jdk

# install gradle
ENV GRADLE_VERSION=7.3
RUN wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -P /tmp
RUN unzip -d /opt/gradle /tmp/gradle-${GRADLE_VERSION}-bin.zip
RUN ln -s /opt/gradle/gradle-${GRADLE_VERSION} /opt/gradle/latest
ENV GRADLE_HOME=/opt/gradle/latest
ENV PATH=${GRADLE_HOME}/bin:${PATH}

# set working directory
WORKDIR /app

# build app
ADD . /app
RUN gradle build

# start app
CMD [ \
    "java", \
    "-jar", \
    "build/libs/MafiaStatisticsAPI-0.0.1-SNAPSHOT.jar", \
    "--spring.config.additional-location=classpath:application-prod.yml" \
]
