package com.ssd.gingermarket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssd.gingermarket.dto.CommentDto;

@Service
public interface CommentInfoService {

	// 댓글 작성
	public void addComment(CommentDto.Request request, Long authorIdx, Long groupIdx);
	
	// 대댓글 작성 
	public void addChildComment(CommentDto.Request request, Long authorIdx, Long groupIdx, Long parentIdx);
	
	// 댓글 목록 가져오기
	public List<CommentDto.Info> getCommentList(Long groupIdx);
	
	// 댓글 수정
	public void updateComment(CommentDto.Request request, Long commentIdx);
	
	// 댓글 삭제
	public void removeComment(Long commentIdx);

}
