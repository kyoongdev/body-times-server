docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
docker volume prune
sleep 3
docker-compose  -f docker-compose.yml up -d