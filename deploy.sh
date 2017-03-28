#!/bin/bash

# updating system only if needed
# apt-get update -y -qq
# apt-get upgrade -y -qq
# installing java jdk 8
apt-get install -y oraclejdk8 -qq
# installing mysql
debconf-set-selections <<< 'mysql-server mysql-server/root_password password iae2016'
debconf-set-selections <<< 'mysql-server mysql-server/root_password_again password iae2016'
apt-get -y install mysql-server
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
# creating a connexion pool
payara41/bin/asadmin create-jdbc-connection-pool --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlDataSource --restype javax.sql.DataSource --property user=root:password=iae2016:DatabaseName=tp_iae:ServerName=localhost:port=3306 tp_iae_pool
payara41/bin/asadmin ping-connection-pool tp_iae_pool
# creating a data source
payara41/bin/asadmin create-jdbc-resource --connectionpoolid tp_iae_pool tp_iae
# deploying app
payara41/bin/asadmin deploy IAE/rdvMed/dist/rdvMed.war
# launching demo
firefox localhost:8080/rdvMed
