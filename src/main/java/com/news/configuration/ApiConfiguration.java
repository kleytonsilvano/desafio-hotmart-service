package com.news.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.news.converter.ProductResponseConverter;

@Configuration
public class ApiConfiguration {
	
	@Bean
	public ProductResponseConverter getProductResponseConverter() {
		return new ProductResponseConverter();
	}

}
