FROM reg.bitgadak.com/closememo/admin-base:0.1

EXPOSE 10091

RUN mkdir -p /home/deployer/deploy
COPY ./build/libs/admin.jar /home/deployer/deploy

ENTRYPOINT java -jar -Dspring.profiles.active=$PROFILE /home/deployer/deploy/admin.jar
