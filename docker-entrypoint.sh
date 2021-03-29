#!/bin/sh

check_host=$1
check_port=$2
shift 2
cmd="$@"

# wait for the check docker to be running
while ! nc -z $check_host $check_port; do
  >&2 echo "ElasticSearch is unavailable - sleeping"
  sleep 1
done

>&2 echo "ElasticSearch is up!!!!!!!!!!!!!!!!!!!!!!!!!111111 - executing command"

# run the command
# exec $cmd
exec mvn spring-boot:run