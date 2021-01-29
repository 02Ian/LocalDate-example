package com.seveneleven.esl.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seveneleven.esl.dto.OauthToken;
import com.seveneleven.esl.dto.Resource;
import com.seveneleven.esl.dto.ResponseDetails;
import com.seveneleven.esl.dto.UserData;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.seveneleven.esl.constant.Constant.*;

@Slf4j
@Component
public class StoreUsersClient {

    @Value("${spring.rest.store-users-svc.url}")
    private String url;

    @Value("${spring.rest.store-users-svc.username}")
    private String username;

    @Value("${spring.rest.store-users-svc.password}")
    private String password;

    @Value("${spring.rest.store-users-svc.oauth-url}")
    private String oauthUrl;

    public Optional<List<UserData>> fetchStoreUserDetail(String storeNumber) throws IOException {
        String storeDataResponse = null;
        String strUrl = url.replaceAll(REPLACE_STR, storeNumber);
        log.info("Request URL: {}", strUrl);
        List<UserData> userDataList = new ArrayList<>();
        Request request = new Request.Builder().url(strUrl).build();
        Response response = createAuthenticatedClient(oauthUrl, username, password).newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        } else {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                storeDataResponse = new String(responseBody.bytes());
                response.close();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                ResponseDetails responseDetails = gson.fromJson(storeDataResponse, ResponseDetails.class);
                List<Resource> resourceList = responseDetails.getResourceList();
                for (Resource resource : resourceList) {
                    UserData userData = new UserData();
                    userData.setUpId(resource.getUser().getUpid());
                    userData.setAddress_1(resource.getUser().getAddress1());
                    userData.setAddress_2(resource.getUser().getAddress2());
                    if (resource.getDisplayName()!= null) {
                        if(resource.getDisplayName().contains(",")) {
                            userData.setFirstName(resource.getDisplayName().substring(resource.getDisplayName().lastIndexOf(",") + 1));
                            userData.setLastName(resource.getDisplayName().substring(0, resource.getDisplayName().lastIndexOf(",")));
                        }
                        else if(resource.getDisplayName().contains(" ")){
                            userData.setFirstName(resource.getDisplayName().substring(resource.getDisplayName().lastIndexOf(" ") + 1));
                            userData.setLastName(resource.getDisplayName().substring(0, resource.getDisplayName().lastIndexOf(" ")));
                        }
                    }
                    userData.setBirthDate(resource.getUser().getDob());
                    userData.setCity(resource.getUser().getCity());
                    userData.setState(resource.getUser().getState());
                    userData.setZipCode(resource.getUser().getZip());
                    userData.setPhone(resource.getUser().getPHONE1HOME());
                    if (resource.getUser().getHireDate() != null) {
                        String[] hireDatesplit = (resource.getUser().getHireDate().split(DELIMITER_COMMA));
                        for (String hireDate : hireDatesplit) {
                            if (hireDate.contains(storeNumber)) {
                                String hiredateActual = hireDate.substring(hireDate.lastIndexOf(DELIMITER_COLON) + 1);
                                userData.setHireDate(hiredateActual);
                            }
                        }
                    }
                    userData.setStore(storeNumber);
                    userDataList.add(userData);
                }
            }
        }
        response.close();
        return Optional.of(userDataList);
    }

    private static OkHttpClient createAuthenticatedClient(final String oauthUrl, final String username,final String password) throws IOException{
        return new OkHttpClient.Builder().authenticator((route, response) -> {
            String token = getOAuthToken(oauthUrl, username, password);
            return response.request().newBuilder().header(AUTHORIZATION_STR, token).build();
        }).build();
    }
    private static String getOAuthToken(final String oauthUrl, final String username, final String password) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        OauthToken oauthToken = null;
        RequestBody requestBody = new FormBody.Builder().add(GRANT_TYPE_STR, CLIENT_CREDENTIALS_STR).build();
        Request request = new Request.Builder().url(oauthUrl).post(requestBody).header(AUTHORIZATION_STR, Credentials.basic(username, password)).build();
        Response response = okHttpClient.newCall(request).execute();
        String responseBody = new String(response.body().bytes());
        if (response.isSuccessful()){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            oauthToken = gson.fromJson(responseBody, OauthToken.class);
        }
        response.close();
        if(oauthToken != null){
            log.debug(oauthToken.getAccessToken());
            return "Bearer ".concat(oauthToken.getAccessToken());
        }else{
            return null;
        }
    }
}