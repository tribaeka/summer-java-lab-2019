MAINTAINER=$(mvn -q \
     -Dexec.executable=echo \
     -Dexec.args='${docker.maintainer}' \
     --non-recursive \
     exec:exec)
echo $MAINTAINER
echo "LABEL maintainer='$MAINTAINER'" >> target/Dockerfile
cd target
docker build -t task:9 .
docker run -it task:9
docker inspect task:9

