package com.ssd.gingermarket.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ssd.gingermarket.domain.Experiod;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ExperiodRepositoryTest {
	
	@Autowired
	private ExperiodRepository experiodRepository;
	
	@Test
	public void addExperiod() {
		// given
		Experiod experiod = Experiod.experiodBuilder()
				.userId((long)1)
				.category("면도기")
				.dDay(100)
				.build();
		
		// when
		Experiod result = experiodRepository.save(experiod);
		
		// then
		assertThat(result.getId()).isNotNull();
		assertThat(result.getUserId()).isEqualTo((long)1);
		assertThat(result.getCategory()).isEqualTo("면도기");
		assertThat(result.getDDay()).isEqualTo(100);

		
	}
	
}
