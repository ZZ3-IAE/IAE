#!/bin/bash

# updating system only if needed
apt-get update -y -qq
# apt-get upgrade -y -qq
# installing java jdk 8
apt-get install -y oraclejdk8 -qq
# installing mysql
apt-get -y install mysql-server -qq
mysqladmin -u root password iae2016
# installing git
apt-get install -y git -qq
# installing firefox
apt-get install -y netbeans -qq
# downloading project
git clone https://github.com/begarco/IAE.git
# installing payara
cp -r IAE/matos/payara41 .
# driver jdbc
cp IAE/matos/mysql-jdbc.jar payara41/glassfish/lib
# creation of database
mysql -u root --password=iae2016 < IAE/matos/script.sql
# starting payara
payara41/bin/asadmin start-domain
# creating a connexion pool
payara41/bin/asadmin create-jdbc-connection-pool --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlDataSource --restype javax.sql.DataSource --property user=root:password=iae2016:DatabaseName=tp_iae:ServerName=localhost:port=3306 tp_iae_pool
payara41/bin/asadmin ping-connection-pool tp_iae_pool
# creating a data source
payara41/bin/asadmin create-jdbc-resource --connectionpoolid tp_iae_pool tp_iae
# build
cd IAE/rdvMed
chmod +rwx ../matos/apache-ant-1.10.1/bin/ant
../matos/apache-ant-1.10.1/bin/ant -Dlibs.CopyLibs.classpath=../matos/org-netbeans-modules-java-j2seproject-copylibstask.jar dist
cd ../..
# deploying app
payara41/bin/asadmin deploy IAE/rdvMed/dist/rdvMed.war
# launching demo
cd IAE/rdvMed
../matos/apache-ant-1.10.1/bin/ant -Dlibs.CopyLibs.classpath=../matos/org-netbeans-modules-java-j2seproject-copylibstask.jar test
../matos/apache-ant-1.10.1/bin/ant -Dlibs.CopyLibs.classpath=../matos/org-netbeans-modules-java-j2seproject-copylibstask.jar javadoc