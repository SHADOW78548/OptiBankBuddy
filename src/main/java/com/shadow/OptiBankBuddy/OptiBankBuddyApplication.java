package com.shadow.OptiBankBuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication

@OpenAPIDefinition(
    info = @Info(
        title = "OptiBankBuddy API",
        version = "1.0",
        description = "API documentation for OptiBankBuddy application",
        contact = @Contact(
            name = "Sujit Mondal",
            email = "sujit.mondal2@cognizant.com",
            url = "https://shadow78548.github.io/My_Portfolio/"
            )
        )
    )
public class OptiBankBuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(OptiBankBuddyApplication.class, args);
	}

}
