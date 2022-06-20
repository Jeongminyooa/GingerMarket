package com.ssd.gingermarket.service;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ssd.gingermarket.domain.GroupBuying;
import com.ssd.gingermarket.dto.GroupBuyingDto;


@Service
public interface GroupBuyingService {

	// 공구 포스트 등록
	public void addPost(GroupBuyingDto.Request groupBuying);

	// 공구 포스트 전체 조회
	public Page<GroupBuying> getAllPost(int page);
	
	// 카테고리 검색 포스트 리스트 가져오기
	public Page<GroupBuying> getAllPostByKeyword(String keyword, int page, String option);
	
	// 공구 포스트 상세 조회
	public GroupBuyingDto.DetailResponse getPost(Long groupIdx);
	
	// 공구 포스트 수정본 상세 조회
	public GroupBuyingDto.Request getPostForModify(Long groupIdx);
	
	// 공구 포스트 수정
	public void modifyPost(Long groupIdx, GroupBuyingDto.Request groupBuying);
	
	// 공구 포스트 삭제
	public void removePost(Long groupIdx);
	
	
}