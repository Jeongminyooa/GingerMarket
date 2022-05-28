package com.ssd.gingermarket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class WebMvcConfig {
	@Bean
    public HiddenHttpMethodFilter httpMethodFilter() {
		//form 태그에서 PUT, DELETE 사용이 가능하도록 설정
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        return hiddenHttpMethodFilter;
    }
	
}

