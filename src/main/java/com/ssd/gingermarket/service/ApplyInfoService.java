package com.ssd.gingermarket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssd.gingermarket.dto.ApplyDto;
import com.ssd.gingermarket.dto.GroupBuyingDto;

@Service
public interface ApplyInfoService {

	// 공구 신청 조회
	public List<ApplyDto.Info> getAllApply(Long groupIdx);
		
	// 공구 신청 등록
	public void addApply(ApplyDto.Info apply, Long userIdx, Long groupIdx);
	
	public List<GroupBuyingDto.MyPageInfo> getAllApplyByAuthor(Long userIdx);

}
