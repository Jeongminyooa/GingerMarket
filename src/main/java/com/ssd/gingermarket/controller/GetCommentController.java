package com.ssd.gingermarket.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssd.gingermarket.domain.CommentInfo;
import com.ssd.gingermarket.dto.CommentDto;
import com.ssd.gingermarket.service.CommentInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j //로그 
@Controller 
@RequestMapping("/comments")
@RequiredArgsConstructor
public class GetCommentController {

	private final CommentInfoService commentInfoService;
	
	// 댓글 리스트 불러오기
	@GetMapping("/{gid}/list")
	public String getCommentList (Model model, @PathVariable(value="gid") Long groupIdx) {
		long userId = 1;
		
		List<CommentDto.Info> commentList = commentInfoService.getCommentList(groupIdx);
	
		model.addAttribute("commentList", commentList);
		return "content/groupBuyingPost/viewGroupPost :: #experiodContent";
	}
	
}
