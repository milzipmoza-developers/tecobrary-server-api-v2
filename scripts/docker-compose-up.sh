# Start Message

echo "################################################################################"
echo "######    I'M TECORVIS. NOW I WILL HELP YOU.                              ######"
echo "######    TECOBRARY-API-SERVER TEST ENVIRONMENT DOCKER SETTING &&         ######"
echo "######                                             DO DOCKER COMPOSE UP   ######"
echo "################################################################################"
echo ""

# Variables

LINE_CONTOUR="################################################################################"

EMPTY=0

TARGET_MYSQL="mysql"
TARGET_MYSQL_VERSION="5.7"

EXIST_IMAGE_INFO=$(docker image ls | grep "$TARGET_MYSQL")

TECORVIS_SAY="TECORVIS ###"

# Docker Mysql Image Find And Pull Image

echo "$TECORVIS_SAY Today is Good Day. Welcome !"
echo ""
echo "$TECORVIS_SAY Check Docker Image [${TARGET_MYSQL}:${TARGET_MYSQL_VERSION}] On Your Computer. "
echo ""
if [[ "$EXIST_IMAGE_INFO" =~ "$TARGET_MYSQL_VERSION" ]]; then
    echo "$TECORVIS_SAY I Found It !"
else
    echo "$TECORVIS_SAY No. We Need To Install !"
    echo "$TECORVIS_SAY Let's Pull [${TARGET_MYSQL}:${TARGET_MYSQL_VERSION}] Image"
    echo ""
    echo "$TECORVIS_SAY Just Wait Few Minutes ... Now I'm Working Hard !"
    echo `docker pull ${TARGET_MYSQL}:${TARGET_MYSQL_VERSION}`
    if [ "#(docker image ls | grep "$TARGET_MYSQL")" == ${EMPTY} ]; then
        echo "$TECORVIS_SAY Something Wrong. Please Execute Me Again."
    fi
    echo "$TECORVIS_SAY [${TARGET_MYSQL}:${TARGET_MYSQL_VERSION}] Image Install Succeed !"
    echo ""
fi

# Build Tecobrary-Api-Server-V2

echo ""
echo "$LINE_CONTOUR"
echo ""

TECOBRARY_IMAGE_INFO=$(docker image ls | grep "tecobrary/tecobrary-api-server")
TECOBRARY_PS_INFO=$(docker image ls | grep "tecobrary/tecobrary-api-server")

echo "$TECORVIS_SAY Checking Old [tecobrary-api-server-v2] Docker Image. Wait Few Minutes..."

if [ ${#TECOBRARY_IMAGE_INFO} == ${EMPTY} ]; then
    echo "$TECORVIS_SAY We Need To Remove Old [tecobrary-api-server-v2] Docker Image."
    echo `docker kill tecobrary/tecobrary-api-server`
    echo `docker rm tecobrary/tecobrary-api-server`
else
    echo "$TECORVIS_SAY No Old [tecobrary-api-server-v2] Docker Image."
fi

echo "$TECORVIS_SAY Now I Will Build [tecobrary-api-server-v2]. Wait Few Minutes..."

# Execute Gradle Clean and Build
echo `./gradlew clean Build`
echo ""
echo ""

# Docker Image Build
echo "$TECORVIS_SAY Now I Will Build [tecobrary-api-server-v2] Docker Image. Wait Few Minutes..."
echo `docker build --build-arg JAR_FILE=build/libs/*.jar -t tecobrary/tecobrary-api-server .`
echo ""
echo ""
echo "$TECORVIS_SAY Congrats ! Image Build Complete !"
echo ""
echo "$TECORVIS_SAY Now Run Test Environment Just Wait"
echo `docker-compose -f ./docker/docker-compose.yml up -d`
echo ""
echo ""
echo "$TECORVIS_SAY Thanks Finished !"