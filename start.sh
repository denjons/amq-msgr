file="$1"


docker-compose -f $file build

docker-compose -f $file up -d

docker-compose -f $file logs -f
