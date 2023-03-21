package com.priceapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.priceapp.repository.IPriceRepository;
import com.priceapp.service.IPriceService;
import com.priceapp.service.impl.PriceService;
/**
 * Application
 * 
 * @author mjpol
 *
 */
@Configuration
public class BeanConfig {	
	
    @Bean
    IPriceService priceService(final IPriceRepository priceRepository) {
        return new PriceService(priceRepository);
    }
		
}
