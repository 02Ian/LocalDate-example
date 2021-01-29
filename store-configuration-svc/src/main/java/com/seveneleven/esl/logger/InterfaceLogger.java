package com.seveneleven.esl.logger;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.Date;

@Slf4j
public class InterfaceLogger {
    private static InterfaceLogger instance = null;
    private static Long instanceId;
    private static String serviceName;

    private InterfaceLogger(Long newInstanceId, String appName){
        instanceId = newInstanceId;
        serviceName = appName;
    }
    private InterfaceLogger(){}

    public static InterfaceLogger getInstance(){
        return instance;
    }

    public static InterfaceLogger getNewInstance(String appName){
        instance = new InterfaceLogger(Instant.now().getEpochSecond(), appName);
        return instance;
    }

    public static Long getInstanceId(){
        return instanceId;
    }

    public void logSuccessMessage(Date eslStartDateTime){
        long duration= 0;
        if (null != eslStartDateTime){
            duration = Instant.now().getEpochSecond() - eslStartDateTime.toInstant().getEpochSecond();
        }
        log.info("InstanceId: {}, Service Name: {}, StartDateTime: {}, EndDateTime: {}, ComputedDuration: {}, Message Type: {}, CreateDate: {}", instanceId, serviceName, eslStartDateTime, new Date(), duration, "Internal-Success", new Date());

    }

    public void logFailureMessage(Date eslStartDateTime, String eslErrorDescription){
        log.error("InstanceId: {}, Service Name: {}, StartDateTime: {}, EndDateTime: {}, Error Description: {}, Message Type: {}, CreateDate: {} ", instanceId, serviceName, eslStartDateTime, new Date(), eslErrorDescription, "Internal-Failure", new Date());
    }

    public void logExternalSuccessMessage(String externalOperationName, Date externalStartDateTime){
        long duration= 0;
        if (null != externalStartDateTime){
            duration = Instant.now().getEpochSecond() - externalStartDateTime.toInstant().getEpochSecond();
        }
        log.info("InstanceId: {}, Service Name: {}, Operation Name : {}, StartDateTime: {}, EndDateTime: {}, ComputedDuration: {}, Message Type: {}, CreateDate: {}", instanceId, serviceName, externalOperationName, externalStartDateTime, new Date(), duration, "External-Success", new Date());
    }

    public void logExternalFailureMessage(String externalOperationName, Date externalStartDateTime, String externalErrorDescription){
        log.error("InstanceId: {}, Service Name: {}, Operation Name : {}, StartDateTime: {}, EndDateTime: {}, Error Description: {}, Message Type: {}, CreateDate: {} ", instanceId, serviceName, externalOperationName, externalStartDateTime, new Date(), externalErrorDescription, "External-Failure", new Date());
    }

}
