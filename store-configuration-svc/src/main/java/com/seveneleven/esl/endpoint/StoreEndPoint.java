package com.seveneleven.esl.endpoint;

import com.seveneleven.dge.esl.messages.pos.getstoreconfig.v1.ConfigRequestType;
import com.seveneleven.dge.esl.messages.pos.getstoreconfig.v1.ConfigResponseType;
import com.seveneleven.dge.esl.services.pos.getstoreconfig.v1.ESLGetStoreConfigurationPortType;
import com.seveneleven.esl.logger.InterfaceLogger;
import com.seveneleven.esl.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.Addressing;
import java.util.Date;

@Service
@Slf4j
public class StoreEndPoint implements ESLGetStoreConfigurationPortType {

    @Autowired
    private StoreService storeService;

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public ConfigResponseType getStoreConfiguration(ConfigRequestType configRequestType){
        InterfaceLogger interfaceLogger = InterfaceLogger.getNewInstance(appName);
        Date startDateTime = new Date();
        ConfigResponseType configResponseType = storeService.getStoreConfiguration(configRequestType);
        interfaceLogger.logSuccessMessage(startDateTime);
        return configResponseType;
    }
}
