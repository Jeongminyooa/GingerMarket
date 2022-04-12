package com.ssd.gingermarket.dto;

import com.ssd.gingermarket.domain.TestEntity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter 
public class TestDto {
	private int testIdx;
	private String userId;
	private String password;
	private String name;
	
	// dto를 entity로 만들기 -> DB 접근을 위해서 
	public TestEntity toEntity() {
		return TestEntity.builder()
				.userId(userId)
				.password(password)
				.name(name)
				.build();
	}
}
