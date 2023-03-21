package com.priceapp.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.priceapp.*")
@PropertySources({
    @PropertySource("classpath:infrastructure.properties")
})
@EnableJpaRepositories(basePackages = {"com.priceapp.repository.springdata"})
@Configuration
@EntityScan("com.priceapp.mapping")
public class InfrastructureConfiguration {
	
}