#!/bin/bash

# updating system
apt-get update -y
apt-get upgrade -y
# installing java jdk 8
apt-get install -y oraclejdk8
# installing mysql
apt-get install -y mysql
# installing git
apt-get install -y git
# downloading project
git clone https://github.com/begarco/IAE.git
# installing payara
unzip IAE/matos/payara.zip .
# driver jdbc
cp IAE/matos/mysql-jdbc.jar payara41/glasfish/lib
