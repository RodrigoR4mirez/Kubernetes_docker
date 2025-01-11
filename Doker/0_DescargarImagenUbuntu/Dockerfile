FROM ubuntu:20.04
RUN apt-get update
RUN apt-get install -y git 
RUN apt-get install -y vim
COPY foo tmp/foo
CMD ["cat", "tmp/foo"]
