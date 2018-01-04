FROM openjdk:8-jre-slim
LABEL name="ui-service" \
      author="bgrochal" \
      organization="device-tracking-system"

# Installing and running Performance Co-Pilot (PCP) with utilities
RUN apt-get update && apt-get install -y curl gnupg apt-transport-https net-tools
RUN curl 'https://bintray.com/user/downloadSubjectPublicKey?username=pcp' | apt-key add -
RUN echo "deb https://dl.bintray.com/pcp/stretch stretch main" | tee -a /etc/apt/sources.list
RUN apt-get update && apt-get install -y pcp pcp-webapi

ADD target/ui-service-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT service pcp start && \
           service pmwebd start && \
           java -jar app.jar --spring.profiles.active=production --spring.config.location=classpath:pl/edu/agh/iet/dts/ui/
