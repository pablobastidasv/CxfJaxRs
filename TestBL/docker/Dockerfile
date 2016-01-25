FROM java
MAINTAINER pablobastidasv@gmail.com

WORKDIR /opt

# Install Tomcat 8
RUN wget http://www.us.apache.org/dist/tomcat/tomcat-8/v8.0.28/bin/apache-tomcat-8.0.28.tar.gz && \
    tar -zxf apache-tomcat-8.0.28.tar.gz && \
    ln -s apache-tomcat-8.0.28 tomcat && \
    rm -rf apache-tomcat-8.0.28.tar.gz

EXPOSE 8080 1043

ENV JPDA_ADDRESS 1043

VOLUME "/opt/tomcat/webapps"

WORKDIR /opt/tomcat
CMD ["bin/catalina.sh", "jpda", "run"]
