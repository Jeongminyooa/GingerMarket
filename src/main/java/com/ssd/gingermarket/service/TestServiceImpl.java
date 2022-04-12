package com.ssd.gingermarket.service;

import org.springframework.stereotype.Service;

import com.ssd.gingermarket.dto.TestDto;
import com.ssd.gingermarket.repository.TestRepository;

import lombok.*;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor //final이 붙은 필드 생성 
@Service
public class TestServiceImpl implements TestService {

	//private final TestRepository testRepository;
	
	@Override
	public Integer insertTest(TestDto dto) {
		// TODO Auto-generated method stub
		log.info("db userId = {}, input password = {}", dto.getUserId(), dto.getPassword());
		return 1;
				//testRepository.save(dto.toEntity()).getTestIdx();
	}

}
