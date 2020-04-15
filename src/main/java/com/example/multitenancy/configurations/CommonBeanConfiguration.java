package com.example.multitenancy.configurations;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonBeanConfiguration {

	@Bean
	public Javers getJaverInstance() {
		return JaversBuilder.javers().build();
	}

}
