package com.ssd.gingermarket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssd.gingermarket.domain.Experiod;
import com.ssd.gingermarket.dto.ExperiodDto;

@Service
public interface ExperiodService {

	// 교체주기 등록
	public void addExperiod(Long userId, String category, int period);
	
	// 교체주기 리스트 가져오기
	public List<ExperiodDto.Info> getAllExperiod(Long userId);
	
	// 교체주기 날짜로 가져오기
	public List<ExperiodDto.Info> getExperiodByDate(Long userId, String date);
	
	// 교체주기 삭제
	public void removeExperiod(Long experiodIdx);
}
