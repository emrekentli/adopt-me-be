package com.emrekentli.adoptme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AdoptMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdoptMeApplication.class, args);
	}

}
