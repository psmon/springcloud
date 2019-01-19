#!/bin/bash

declare IFCONFIG="/sbin/ifconfig"
function hotInterface() {
	local result=$( netstat -rn | grep default | sed -E "1,1s/[ ]+/ /g" | cut -d\  -f 6 )
	if [ $result = "ppp0" ]
	then
		result="lo0"
	fi
	echo $result
}

function createAlias() {
	$IFCONFIG $( hotInterface ) $1 alias netmask 255.255.255.255
}

function deleteAlias() {
	if [ "$1" != "" ]
	then
		$IFCONFIG $( hotInterface ) $1 -alias
	fi
}


function deleteMachine() {
	local name=$1
	local record=$( grep "$name # AVPN" /etc/hosts )

	if [ "$record" != "" ]
	then
		echo $record | while read address name dummyHash dummyAVPN isAlias
		do
			if [ "$isAlias" = "YES" ]
			then
				deleteAlias $address
			fi
		done
		local tmpHosts=$( mktemp -t avpn.hosts.XXXXXXXX )
		grep -v "$name # AVPN" /etc/hosts >$tmpHosts
		mv $tmpHosts /etc/hosts
		chmod +r /etc/hosts
	fi
}

function addMachine() {
	local address=$1
	local name=$2
	local isAlias=${3:-YES}

	deleteMachine $name

	if [ "$isAlias" = "YES" ]
	then
		createAlias $address
		echo "$address $name # AVPN YES" >>/etc/hosts
	else
		echo "$address $name # AVPN " >>/etc/hosts
	fi

}

addMachine 10.0.10.1 docker-spring-mysql.local
addMachine 10.0.10.2 docker-spring-zookeeper.local
addMachine 10.0.10.3 docker-spring-kafka.local
addMachine 10.0.10.11 docker-spring-mysqladmin.local
