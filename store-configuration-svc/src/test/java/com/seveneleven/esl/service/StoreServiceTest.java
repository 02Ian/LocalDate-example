package com.seveneleven.esl.service;

import com.seveneleven.dge.esl.entities.pos.header.v1.APIRequestType;
import com.seveneleven.dge.esl.messages.pos.getstoreconfig.v1.ConfigRequestType;
import com.seveneleven.esl.entity.StoreConfigurationV22;
import com.seveneleven.esl.repository.StoreRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class StoreServiceTest {

    @InjectMocks
    private StoreService storeService;

    @Mock
    private StoreRepository storeRepository;

    @Test
    public void getConfigurationStatusTest()  {
        Mockito.when(storeRepository.findByStoreID(getGetStoreConfiguration().getAPIRequest().getStoreId())).thenReturn(storeConfigurationV22List());
        storeService.getStoreConfiguration(getGetStoreConfiguration());
    }

    @Test
    public void getConfigurationStatusTestN()  {
        Mockito.when(storeRepository.findByStoreID(getGetStoreConfiguration().getAPIRequest().getStoreId())).thenReturn(storeConfigurationV22ListDGE());
        storeService.getStoreConfiguration(getGetStoreConfiguration());
    }

    @Test
    public void getConfigurationStatusTestNegative(){
        Mockito.when(storeRepository.findByStoreID(getGetStoreConfiguration().getAPIRequest().getStoreId())).thenReturn(storeConfigurationV22ListNegative());
        storeService.getStoreConfiguration(getGetStoreConfiguration());
    }

    public ConfigRequestType getGetStoreConfiguration() {
        ConfigRequestType getStoreConfiguration = new ConfigRequestType();
            APIRequestType apiRequestType = new APIRequestType();
            apiRequestType.setDGETransSequence("0");
            apiRequestType.setPOSTransSequence("16006305");
            apiRequestType.setChannel(3);
            apiRequestType.setStoreId(18215);
            apiRequestType.setMessageFormatVersion("1.0");
        getStoreConfiguration.setAPIRequest(apiRequestType);
    return getStoreConfiguration;
    }

    private List<StoreConfigurationV22> storeConfigurationV22List(){
        List<StoreConfigurationV22> storeConfigurationV22Liststub = new ArrayList<>();
            StoreConfigurationV22 storeConfigurationV22stub = new StoreConfigurationV22();
            storeConfigurationV22stub.setStoreId(18567);
            storeConfigurationV22stub.setDgeConfigId("3");
            storeConfigurationV22stub.setDgeParameterName("FuelCashPriceDifferential");
            storeConfigurationV22stub.setDgeParameterValue("T");
        storeConfigurationV22Liststub.add(storeConfigurationV22stub);
        return storeConfigurationV22Liststub;
    }
    private List<StoreConfigurationV22> storeConfigurationV22ListDGE(){
        List<StoreConfigurationV22> storeConfigurationV22Liststub = new ArrayList<>();
        StoreConfigurationV22 storeConfigurationV22stub = new StoreConfigurationV22();
        storeConfigurationV22stub.setStoreId(18567);
        storeConfigurationV22stub.setDgeConfigId("3");
        storeConfigurationV22stub.setDgeParameterName("FuelCashPrice");
        storeConfigurationV22stub.setDgeParameterValue("T");
        storeConfigurationV22Liststub.add(storeConfigurationV22stub);
        return storeConfigurationV22Liststub;
    }

    private List<StoreConfigurationV22> storeConfigurationV22ListNegative(){
        return new ArrayList<>();
    }
}
