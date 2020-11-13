package com.hotmart.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class JdbcConfiguration {

//    @Autowired
//    private DataSourceProperties properties;
//    
//	@Bean
//	@ConfigurationProperties(prefix = "spring.datasource")
//	public DataSource dataSource() {
//		DataSourceBuilder<?> factory = DataSourceBuilder.create(this.properties.getClassLoader())
//                .driverClassName(this.properties.getDriverClassName()).url(this.properties.getUrl());
//		return factory.build();  
//	}

}
