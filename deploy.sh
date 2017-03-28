#!/bin/bash

# updating system
apt-get update -y -qq
apt-get upgrade -y -qq
# installing java jdk 8
apt-get install -y oraclejdk8 -qq
# installing mysql
apt-get install -y mysql -qq
# installing git
apt-get install -y git -qq
# installing firefox
apt-get install -y firefox -qq
# downloading project
git clone https://github.com/begarco/IAE.git
# installing payara
cp -r IAE/matos/payara41 .
# driver jdbc
cp IAE/matos/mysql-jdbc.jar payara41/glasfish/lib
# starting payara
payara41/bin/asadmin start-domain
# deploying app
payara41/bin/asadmin deploy IAE/rdvMed/dist/rdvMed.war
# launching demo
firefox localhost:8080/rdvMed
