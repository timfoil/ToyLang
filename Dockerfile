FROM alpine:3.12

RUN apk update \ 
    && apk upgrade \
    && apk add --no-cache bash \
    build-base \
    vim \
    git \
    make \
    cmake \
    python3 \
    && ln -sf python3 /usr/bin/python

WORKDIR "/root"

RUN git clone --branch version_97 https://github.com/WebAssembly/binaryen.git 

WORKDIR "/root/binaryen"

RUN cmake . \
    && make

ENV PATH "$PATH:/root/binaryen/bin/"

ENTRYPOINT [ "/bin/bash" ]
