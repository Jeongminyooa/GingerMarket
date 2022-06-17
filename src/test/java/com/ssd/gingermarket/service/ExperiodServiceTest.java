package com.ssd.gingermarket.service;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ssd.gingermarket.domain.Experiod;
import com.ssd.gingermarket.exception.ExperiodException;
import com.ssd.gingermarket.repository.ExperiodRepository;

@ExtendWith(MockitoExtension.class)
public class ExperiodServiceTest {

	private final Long userIdx = (long)1;
	private final String category = "면도기";
	private final int period = 100;
	
	@InjectMocks
	private ExperiodService service;
	
	@Mock
	private ExperiodRepository repository;
	
	@Test
	public void addExperiod() {
		// given
		//doReturn(experid()).when(repository).findByUserId(userIdx);
	
		// when
		//final ExperiodException result = assertThrows(ExperiodException.class, () -> service.addExperiod(userIdx, category, period)); 
	}
	
	private Experiod experid() {
		return Experiod.experiodBuilder()
				.userId(userIdx)
				.category(category)
				.dDay(period)
				.build();
	}
}
