package com.seveneleven.esl.controller;

import com.seveneleven.esl.dto.APIResponse;
import com.seveneleven.esl.dto.UserData;
import com.seveneleven.esl.service.StoreUsersClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import java.io.IOException;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class GetStoreUsersController {


    @Autowired
    private StoreUsersClient storeUsersClient ;

    @GetMapping(value = "/storeusers/{store}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity< Map<String, Object>> getStoreUsers(@PathVariable("store") String storeNumber)  throws RestClientException, IOException {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setRequestDateTime(new Date());
        Map<String, Object> responseMap = new LinkedHashMap<>();
        log.debug("Controller heading to fetch the data");
        Optional<List<UserData>> storeUsersResponse = storeUsersClient.fetchStoreUserDetail(storeNumber);
        apiResponse.setStatus("0");
        apiResponse.setStatusDescription("Success");
        log.debug("API Response being loaded.");
        responseMap.put("apiResponse",apiResponse);
        responseMap.put("userData", storeUsersResponse.orElse(new ArrayList<>()));
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }
}
