package com.hotmart.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hotmart.converter.ProductModelConverter;

@Configuration
public class ApiConfiguration {
	
	@Bean
	public ProductModelConverter getProductModelConverter() {
		return new ProductModelConverter();
	}

}
