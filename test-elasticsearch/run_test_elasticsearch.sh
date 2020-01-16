EMPTY=0

ES_IMAGE_INFO=$(docker image ls | grep "test-elasticsearch")

if [ ${#ES_IMAGE_INFO} != ${EMPTY} ]; then
    echo "$TECORVIS_SAY We Need To Remove Old [test-elasticsearch] Docker Image."
    echo `docker rmi -f test-elasticsearch`
else
    echo "$TECORVIS_SAY No Old [test-elasticsearch] Docker Image."
fi

ES_CONTAINER_RUNNING_INFO=$(docker ps | grep "test-elasticsearch")

if [ ${#ES_CONTAINER_RUNNING_INFO} != ${EMPTY} ]; then
    echo "$TECORVIS_SAY We Need To Remove Old [test-elasticsearch] Docker Image."
    echo `docker stop test-elasticsearch`
    echo `docker rm test-elasticsearch`
else
    echo "$TECORVIS_SAY No Old [test-elasticsearch] Docker Image."
fi

docker build -t test-elasticsearch ./test-elasticsearch

docker run -d -it -p 9292:9200 -p 9393:9300 -e "ES_JAVA_OPTS=-Xms512m -Xmx512m" --name test-elasticsearch test-elasticsearch

for i in {30..0}; do
  echo "[test-elasticsearch] Checking Elasticsearch is Started..."
  echo ""
  curl -s -o /dev/null localhost:9292
  if  [ "$?" != "7" ] && [ $(curl -fsSL "http://localhost:9292/_cat/health?h=status" | grep -E '^green') ]; then
    echo "------------------------------------------------------------"
    echo "[test-elasticsearch] Elasticsearch is Started!"
    break
  fi
  sleep 2
done
