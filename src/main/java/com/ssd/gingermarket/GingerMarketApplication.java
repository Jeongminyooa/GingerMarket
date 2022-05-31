package com.ssd.gingermarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableScheduling // 스케줄링 사용
@SpringBootApplication()
public class GingerMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(GingerMarketApplication.class, args);
	}
	
}
