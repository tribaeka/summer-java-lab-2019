FROM centos:7
RUN yum -y update; \
     yum install -y java-1.8.0-openjdk;
COPY task-9-1.0-SNAPSHOT.jar /app/
RUN java -jar app/task-9-1.0-SNAPSHOT.jar
