package com.ssd.gingermarket.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.ssd.gingermarket.dto.ApplyDto;
import com.ssd.gingermarket.dto.GroupBuyingDto;

@Service
public interface GroupBuyingService {

	// 공구 포스트 등록
	public void addPost(GroupBuyingDto.Request groupBuying);

	// 공구 포스트 전체 조회
	public List<GroupBuyingDto.CardResponse> getAllPost();
	
	// 카테고리 검색 포스트 리스트 가져오기
	//public List<GroupBuyingDto.Info> getGroupBuyingByCategory(String category);
	
	// 제목 검색 포스트 리스트 가져오기
	//public List<GroupBuyingDto.Info> getGroupBuyingByTitle(String title);
	
	// 공구 포스트 상세 조회
	public GroupBuyingDto.DetailResponse getPost(Long groupIdx);
	
	// 공구 포스트 수정본 상세 조회
	public GroupBuyingDto.Request getPostForModify(Long groupIdx);
	
	// 공구 포스트 수정
	public void modifyPost(Long groupIdx, GroupBuyingDto.Request groupBuying);
	
	// 공구 포스트 삭제
	public void removePost(Long groupIdx);
	
	// 공구 신청 조회
	public List<ApplyDto.Response> getAllApply();
	
	// 공구 신청 등록
	public void addApply(ApplyDto.Request apply, Long groupIdx);

	
}