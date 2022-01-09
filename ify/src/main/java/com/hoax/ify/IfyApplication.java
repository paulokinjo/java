package com.hoax.ify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class IfyApplication {

	public static void main(String[] args) {
		SpringApplication.run(IfyApplication.class, args);
	}

}
