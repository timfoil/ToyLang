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
    openjdk11 \
    && ln -sf python3 /usr/bin/python

WORKDIR "/root"

# grab binaryen from github
RUN git clone --branch version_97 https://github.com/WebAssembly/binaryen.git 

# grab emscripten from github
# RUN git clone --branch 2.0.4 https://github.com/emscripten-core/emsdk.git

WORKDIR "/root/binaryen"

# build binaryen
RUN cmake . \
    && make


# WORKDIR "/root/emsdk"

# # build emscripten
# RUN ./emsdk install latest \
#     && ./emsdk activate latest \
#     && source ./emsdk_env.sh

WORKDIR "/root"

# set environment variables
ENV PATH "$PATH:/root/binaryen/bin/"
ENV JAVA_HOME "/usr/lib/jvm/java-11-openjdk/"


# copy dockerfile
COPY ./Dockerfile /root

ENTRYPOINT [ "/bin/bash" ]
