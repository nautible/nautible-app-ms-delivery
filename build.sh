#!/bin/sh
mvn clean package -P $1 -Dquarkus.package.type=fast-jar -DskipTests=true
if [ "$?" -eq 0 ]; then
  cd nautible-app-ms-delivery-build && docker build -t $IMAGE -f ./src/main/docker/Dockerfile.jvm .
else
  echo "[ERROR] ----------------------[ maven build error ]-----------------------"
  exit 1
fi
