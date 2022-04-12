package com.ssd.gingermarket.service;

import org.springframework.stereotype.Service;

import com.ssd.gingermarket.dto.TestDto;

@Service
public interface TestService {

	public Integer insertTest(TestDto dto);
}
