package com.seveneleven.esl.endpoint;

import com.seveneleven.dge.esl.entities.pos.header.v1.APIRequestType;
import com.seveneleven.dge.esl.messages.pos.getstoreconfig.v1.ConfigRequestType;
import com.seveneleven.esl.service.StoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
public class StoreEndPointTest {

    @InjectMocks
    private StoreEndPoint storeEndPoint;

    @Mock
    private StoreService storeService;

    @Test
    public void getConfigurationStatusTest() throws IOException, SQLException {
        storeEndPoint.getStoreConfiguration(getGetStoreConfigurationRequest());
    }

    private ConfigRequestType getGetStoreConfigurationRequest() {
        ConfigRequestType getStoreConfigurationstub = new ConfigRequestType();
        getStoreConfigurationstub.setAPIRequest(getApiRequestType());
        return getStoreConfigurationstub;
    }
    private APIRequestType getApiRequestType(){
        APIRequestType apiRequestType = new APIRequestType();
            apiRequestType.setDGETransSequence("0");
            apiRequestType.setPOSTransSequence("16006305");
            apiRequestType.setChannel(3);
            apiRequestType.setStoreId(18215);
            apiRequestType.setMessageFormatVersion("1.0");
        return apiRequestType;
    }
}
