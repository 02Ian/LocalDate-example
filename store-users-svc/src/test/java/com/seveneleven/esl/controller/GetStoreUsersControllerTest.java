package com.seveneleven.esl.controller;

import com.seveneleven.esl.dto.APIResponse;
import com.seveneleven.esl.dto.UserData;
import com.seveneleven.esl.service.StoreUsersClient;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;


@RunWith(MockitoJUnitRunner.class)
public class GetStoreUsersControllerTest {

    @Mock
    StoreUsersClient storeUsersClient;

    @Spy
    APIResponse apiResponse = new APIResponse();

    @InjectMocks
    GetStoreUsersController getStoreUsersController;

    @Test
    public void getStoreUsersTestSuccess() throws JSONException, IOException {
        apiResponse.setRequestDateTime(new Date());
        String storeNumber = "32266";
        Mockito.when(storeUsersClient.fetchStoreUserDetail(storeNumber)).thenReturn(getrecords());
        getStoreUsersController.getStoreUsers(storeNumber);
    }


    @Test
    public void getStoreUsersTestNegative() throws JSONException, IOException {
        apiResponse.setRequestDateTime(new Date());
        String storeNumber = "32266";
        getStoreUsersController.getStoreUsers(storeNumber);
    }

    public Optional<List<UserData>> getrecords() {
        List list = new ArrayList();
        UserData userData = new UserData();
        userData.setUpId("CH_005676");
        userData.setAddress_1("2733 COUNTRY CLUB BLVD 86");
        userData.setAddress_2("null");
        userData.setPhone("9255774699");
        userData.setZipCode("72399");
        userData.setFirstName("Jude");
        userData.setLastName("Njoku");
        userData.setCity("EAST STOCKTON");
        userData.setHireDate("32266:08/16/2010, 35485:01/03/2013");
        userData.setBirthDate("10/13/1990");
        list.add(userData);

        return Optional.of(list);
    }
}
