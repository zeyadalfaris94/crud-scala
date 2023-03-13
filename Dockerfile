FROM openjdk:11

LABEL maintainer="zeyad.alfaris94@gmail.com"

ENV SCALA_VERSION 2.13.8
ENV SBT_VERSION 1.8.2

RUN apt-get update && apt-get install -y curl tar bash procps wget && apt-get clean && rm -rf /var/lib/apt/lists/*

## Then, download and install sbt
RUN curl -sL "https://github.com/sbt/sbt/releases/download/v${SBT_VERSION}/sbt-${SBT_VERSION}.tgz" | gunzip | tar -x -C /usr/local && \
    ln -s /usr/local/sbt/bin/* /usr/local/bin/

## Download and install Scala
RUN wget https://downloads.lightbend.com/scala/$SCALA_VERSION/scala-$SCALA_VERSION.tgz && \
    tar -xzf scala-$SCALA_VERSION.tgz && \
    mv scala-$SCALA_VERSION /usr/share/scala && \
    ln -s /usr/share/scala/bin/* /usr/local/bin/

WORKDIR /app

COPY . .

RUN sbt compile

EXPOSE 8080

CMD ["sbt", "run"]

#ENTRYPOINT exec java \
#         -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=9010 \
#         -Dcom.sun.management.jmxremote.rmi.port=9010 \
#         -Dcom.sun.management.jmxremote.local.only=false \
#         -Dcom.sun.management.jmxremote.authenticate=false \
#         -Dcom.sun.management.jmxremote.ssl=false \
#         -Djava.rmi.server.hostname=127.0.0.1 \
#         -XX:InitialRAMPercentage=65.0 -XX:MinRAMPercentage=65.0 -XX:MaxRAMPercentage=65.0 -XX:ActiveProcessorCount=2 \
#         -XX:+AlwaysPreTouch -XX:NativeMemoryTracking=summary -XX:+UseG1GC \
#         -jar crud-scala.jar
