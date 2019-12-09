echo "################################################################################"
echo "######    I'M TECORVIS. NOW I WILL HELP YOU.                              ######"
echo "######    DO DOCKER COMPOSE DOWN                                          ######"
echo "################################################################################"

TECORVIS_SAY="TECORVIS ###"

echo "$TECORVIS_SAY [$(docker kill api-server)] KILL COMPLETE !"
echo "$TECORVIS_SAY [$(docker kill mysql-db)] KILL COMPLETE !"
echo ""
echo "$TECORVIS_SAY [$(docker rm api-server)] REMOVE COMPLETE !"
echo "$TECORVIS_SAY [$(docker rm mysql-db)] REMOVE COMPLETE !"