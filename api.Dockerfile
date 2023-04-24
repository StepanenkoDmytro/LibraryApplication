FROM maven:3.3-jdk-8
COPY . /application
WORKDIR /application
RUN mvn spring-boot:run
