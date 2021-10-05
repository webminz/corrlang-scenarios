#!/usr/bin/env bash


source ../globalVars.properties
export JAVA_HOME="$JRE_INSTALL_DIR/Contents/Home"
mkdir -p logs

GP_LOG_FILE="logs/GP_APP_$(date +"%Y-%m-%d").log"
rm $GP_LOG_FILE
(cd fhir-endpoint/;./gradlew runGPApp) > $GP_LOG_FILE  &

HOSP_LOG_FILE="logs/HOSP_APP_$(date +"%Y-%m-%d").log"
rm $HOSP_LOG_FILE
(cd fhir-endpoint/;./gradlew runHospitalApp) > $HOSP_LOG_FILE &

LAB_LOG_FILE="logs/LAB_APP_$(date +"%Y-%m-%d").log"
rm $LAB_LOG_FILE
(cd bloodtest-endpoint/;./gradlew bootRun) > $LAB_LOG_FILE &

echo "Waiting for endpoints to start up (usually takes around 5-10 seconds)"
sleep 4

while [ -z "$GP_PID" ]; do
	GP_PID=$(less $GP_LOG_FILE | grep "Started SimpleFHIRApplication" | awk '{print $4}')
	if [ -z "$GP_PID" ] 
	then
		sleep 1
	else
		echo "Endpoint 'GP' (PID=$GP_PID) running at http://localhost:9001/graphql"
	fi
done

while [ -z "$HOSP_PID" ]; do
	HOSP_PID=$(less $HOSP_LOG_FILE | grep "Started SimpleFHIRApplication" | awk '{print $4}')
	if [ -z "$HOSP_PID" ]
	then
		sleep 1
	else
		echo "Endpoint 'HOSP' (PID=$HOSP_PID) running at http://localhost:9002/graphql"
	fi
done

while [ -z "$LAB_PID" ]; do
	LAB_PID=$(less $LAB_LOG_FILE | grep "Started BloodTestDBApplication" | awk '{print $4}')
	if [ -z "$LAB_PID" ]
	then
		sleep 1
	else
		echo "Endpoint 'LAB' (PID=$LAB_PID) running at http://localhost:9003/graphql"
	fi
done

read -p "Press any key to terminate"
kill -9 $GP_PID
kill -9 $HOSP_PID
kill -9 $LAB_PID
echo "...Terminated!"


