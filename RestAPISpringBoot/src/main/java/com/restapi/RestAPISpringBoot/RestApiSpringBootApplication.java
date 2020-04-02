package com.restapi.RestAPISpringBoot;

import com.restapi.controller.EmployeeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@	ComponentScan(basePackageClasses= EmployeeController.class)
public class RestApiSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiSpringBootApplication.class, args);
    }

}
