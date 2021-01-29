package com.seveneleven.esl;

import com.seveneleven.esl.service.StoreUsersClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreUsersSvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreUsersSvcApplication.class, args);
    }
}
