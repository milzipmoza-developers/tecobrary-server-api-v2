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