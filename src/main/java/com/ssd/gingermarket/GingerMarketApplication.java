package com.ssd.gingermarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class GingerMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(GingerMarketApplication.class, args);
	}
	
}
