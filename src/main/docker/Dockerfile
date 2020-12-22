FROM maven:3.6.3-openjdk-15

WORKDIR /app

RUN git clone --depth 1 --no-tags https://github.com/stef2georg/test-parent
RUN cd test-parent && mvn package

COPY run.sh run.sh

# $0 is username and $1 is password/token for Github
ENTRYPOINT chmod +x run.sh && ./run.sh $0 $1