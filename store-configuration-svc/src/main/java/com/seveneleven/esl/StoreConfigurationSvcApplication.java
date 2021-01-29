package com.seveneleven.esl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class StoreConfigurationSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreConfigurationSvcApplication.class, args);
	}

}
