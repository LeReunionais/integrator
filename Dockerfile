FROM maven:3.2.5-jdk-8

WORKDIR /entry

ADD pom.xml /entry/pom.xml
RUN mvn dependency:resolve
RUN mvn dependency:resolve-plugins
RUN mvn verify

ADD src /entry/src
RUN mvn compile assembly:single

CMD java -jar target/Integrator-1.0-SNAPSHOT-jar-with-dependencies.jar
