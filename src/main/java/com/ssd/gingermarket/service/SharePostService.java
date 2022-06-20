package com.ssd.gingermarket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssd.gingermarket.dto.SharePostDto;
import com.ssd.gingermarket.dto.SharePostDto.Request;

@Service
public interface SharePostService {

	//포스트 등록 
	public void addPost(SharePostDto.Request dto);
	
	//포스트 상세조회
	public SharePostDto.DetailResponse getPost(Long postIdx);
	
	//포스트 상세조회(update)
	public SharePostDto.Request getPostForModify(Long postIdx);
	
	//사용자가 작성한 포스트리스트 조회
	public List<SharePostDto.MyPageInfo> getPostByUserId(Long userIdx);
	
	//전체 포스트리스트 조회 
	public List<SharePostDto.CardResponse> getAllPost();
	
	//포스트 업데이트 
	public void modifyPost(Long postIdx, SharePostDto.Request dto);
	
	//포스트 삭제
	public void removePost(Long postIdx);

	//나눔 상태 업데이트 
	public void modifyProgress(Long postIdx, boolean prog);
	
}
