package com.seveneleven.esl.service;

import com.google.common.base.Stopwatch;
import com.seveneleven.dge.esl.entities.common.v1.APIResponseType;
import com.seveneleven.dge.esl.entities.common.v1.ConfigParamType;
import com.seveneleven.dge.esl.messages.pos.getstoreconfig.v1.ConfigRequestType;
import com.seveneleven.dge.esl.messages.pos.getstoreconfig.v1.ConfigResponseType;
import com.seveneleven.esl.entity.StoreConfigurationV22;

import com.seveneleven.esl.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    List<StoreConfigurationV22> storeConfigurationV22List =  new ArrayList<>();

    public ConfigResponseType getStoreConfiguration(ConfigRequestType getConfigurationStatusRequest)  {
        try{
            Stopwatch stopwatch = Stopwatch.createStarted();
            int storeId = getConfigurationStatusRequest.getAPIRequest().getStoreId();
            APIResponseType apiResponseType = new APIResponseType();
            ConfigResponseType configResponseType = new ConfigResponseType();
            ConfigResponseType.ConfigParams configParams = new ConfigResponseType.ConfigParams();

            storeConfigurationV22List = storeRepository.findByStoreID(storeId);

            for (StoreConfigurationV22 storeConfigurationV22 : storeConfigurationV22List) {

                ConfigParamType configParamType = new ConfigParamType();
                    if (storeConfigurationV22.getDgeParameterName().contains("FuelCashPriceDifferential")) {
                        configParamType.setName("FuelCashPriceDifferentialAmount");
                    } else {
                        configParamType.setName(storeConfigurationV22.getDgeParameterName());
                    }
                configParamType.setID(storeConfigurationV22.getDgeConfigId());
                configParamType.setValue(storeConfigurationV22.getDgeParameterValue());
                configParams.getConfigParams().add(configParamType);
            }

            if (storeConfigurationV22List.size() > 0) {
                apiResponseType.setStatus(0);
            } else {
                apiResponseType.setStatus(2);
            }
            apiResponseType.setDGETransSequence(getConfigurationStatusRequest.getAPIRequest().getDGETransSequence());
            apiResponseType.setPOSTransSequence(getConfigurationStatusRequest.getAPIRequest().getPOSTransSequence());
            apiResponseType.setExecutionDuration((int) stopwatch.stop().elapsed(TimeUnit.SECONDS));
            apiResponseType.setMessageFormatVersion(getConfigurationStatusRequest.getAPIRequest().getMessageFormatVersion());
            apiResponseType.setRequestDateTime(getConfigurationStatusRequest.getAPIRequest().getRequestDateTime());
            apiResponseType.setStoreId(getConfigurationStatusRequest.getAPIRequest().getStoreId());

            configResponseType.setAPIResponse(apiResponseType);
            configResponseType.setConfigParams(configParams);
            log.info("Execution Completed");
            return configResponseType;
        }catch (Exception e){
            log.error("Error occurred while invoking Store Configuration service", e);
        }
        return null;
    }
}

