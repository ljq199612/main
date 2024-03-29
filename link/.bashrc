# Sample .bashrc for SuSE Linux
# Copyright (c) SuSE GmbH Nuernberg

# There are 3 different types of shells in bash: the login shell, normal shell
# and interactive shell. Login shells read ~/.profile and interactive shells
# read ~/.bashrc; in our setup, /etc/profile sources ~/.bashrc - thus all
# settings made here will also take effect in a login shell.
#
# NOTE: It is recommended to make language settings in ~/.profile rather than
# here, since multilingual X sessions would not work properly if LANG is over-
# ridden in every subshell.

# Some applications read the EDITOR variable to determine your favourite text
# editor. So uncomment the line below and enter the editor of your choice :-)
#export EDITOR=/usr/bin/vim
#export EDITOR=/usr/bin/mcedit

# For some news readers it makes sense to specify the NEWSSERVER variable here
#export NEWSSERVER=your.news.server

# If you want to use a Palm device with Linux, uncomment the two lines below.
# For some (older) Palm Pilots, you might need to set a lower baud rate
# e.g. 57600 or 38400; lowest is 9600 (very slow!)
#
#export PILOTPORT=/dev/pilot
#export PILOTRATE=115200


test -s ~/.alias && . ~/.alias || true

stty -ixon
xhost +




###
export PATH=/home/ljq/.local/bin:$PATH

export JAVA_HOME=/home/ljq/opt/jdk8
export JRE_HOME=$JAVA_HOME/jre
export CLASSPATH=.:$JAVA_HOME/lib:$JRE_HOME/lib
export PATH=$JAVA_HOME/bin:$PATH


# oracle 客户端
export ORACLE_HOME=/home/ljq/opt/oracle-client/18/
export TNS_ADMIN=$ORACLE_HOME/network/admin
export NLS_LANG=AMERICAN_AMERICA.ZHS16GBK
export NLS_LANG=AMERICAN_AMERICA.AL32UTF8
export LD_LIBRARY_PATH=$ORACLE_HOME
export PATH=$ORACLE_HOME:$PATH


# zookeeper
export ZK_HOME=/home/ljq/opt/apache-zookeeper
export PATH=$ZK_HOME/bin:$PATH
alias  zookeeper-server='zkServer.sh'

# nacos-service
# 系统会生成 derby.log 文件和 logs/ work/ 目录，下面这样折腾，这几个文件会在 nacos 目录下创建
alias  nacos-service.start='cd /home/ljq/opt/nacos/ && sh ./bin/startup.sh  -m standalone && cd'
alias  nacos-service.stop='sh /home/ljq/opt/nacos/bin/shutdown.sh'

# 
alias cdd_='cd ~/public/gitRepo/io/Volume'

