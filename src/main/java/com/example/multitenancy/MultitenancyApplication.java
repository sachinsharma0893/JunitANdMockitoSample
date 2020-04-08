package com.example.multitenancy;

import static org.springframework.core.env.AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableJpaAuditing
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class MultitenancyApplication {

	public static void main(String[] args) {
		System.out.print("***************** "+DEFAULT_PROFILES_PROPERTY_NAME);
		SpringApplication.run(MultitenancyApplication.class, args);
	}

}
