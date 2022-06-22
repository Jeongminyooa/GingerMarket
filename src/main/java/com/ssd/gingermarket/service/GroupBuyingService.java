package com.ssd.gingermarket.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ssd.gingermarket.domain.GroupBuying;
import com.ssd.gingermarket.dto.GroupBuyingDto;
import com.ssd.gingermarket.dto.GroupBuyingDto.DetailResponse;
import com.ssd.gingermarket.dto.SharePostDto;


@Service
public interface GroupBuyingService {

	// 공구 포스트 등록
	public void addPost(GroupBuyingDto.Request groupBuying);

	// 공구 포스트 전체 조회
	public Page<GroupBuyingDto.DetailResponse> getAllPost(int page);
	
	// 카테고리 검색 포스트 리스트 가져오기
	public Page<GroupBuyingDto.DetailResponse> getAllPostByKeyword(String keyword, int page, String option);
	
	// 선호 포스트 조회
	public List<DetailResponse> getFavPost(Long userIdx);
  
	// 사용자가 작성한 공구 포스트 리스트
	public List<GroupBuyingDto.MyPageInfo> getGroupBuyingByUserIdx(Long userIdx);
	
	// 공구 포스트 상세 조회
	public GroupBuyingDto.DetailResponse getPost(Long groupIdx, Long userIdx);
	
	// 공구 포스트 수정본 상세 조회
	public GroupBuyingDto.Request getPostForModify(Long groupIdx);
	
	// 공구 포스트 수정
	public void modifyPost(Long groupIdx, GroupBuyingDto.Request groupBuying);
	
	// 공구 포스트 삭제
	public void removePost(Long groupIdx);
	
	
}