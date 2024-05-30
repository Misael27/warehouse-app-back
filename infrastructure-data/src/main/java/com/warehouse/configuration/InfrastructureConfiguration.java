package com.warehouse.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.warehouse.*")
@PropertySources({
    @PropertySource("classpath:infrastructure.properties")
})
@EnableJpaRepositories(basePackages = {"com.warehouse.repository.springdata"})
@Configuration
@EntityScan("com.warehouse.mapping")
public class InfrastructureConfiguration {
	
}