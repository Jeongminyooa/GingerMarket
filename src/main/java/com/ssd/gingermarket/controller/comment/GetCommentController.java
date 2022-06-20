package com.ssd.gingermarket.controller.comment;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	@GetMapping("/{gid}")
	public String getCommentList (Model model,
			@PathVariable(value="gid") Long groupIdx,
			HttpServletRequest req) {
		/*
		HttpSession session = req.getSession(false);
		Long userIdx = (Long) session.getAttribute("userIdx");
		 */
		
		List<CommentDto.Info> commentList = commentInfoService.getCommentList(groupIdx);
	
		// 로그인한 사용자의 id
		model.addAttribute("userIdx", (long)2);
		
		// 포스트 작성자 id
		model.addAttribute("commentList", commentList);
		
		return "content/groupBuyingPost/groupPost_view :: #commentContent";
	}
}
