package com.ssd.gingermarket.controller.mypage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssd.gingermarket.dto.SharePostDto;
import com.ssd.gingermarket.service.MessageService;
import com.ssd.gingermarket.service.SharePostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/history")
@RequiredArgsConstructor
public class GetHistoryOfShareController {

	private final SharePostService sharePostService;
	private final MessageService messageService;
	
	@GetMapping("/share")
	public String getShareHistory (Model model,
			HttpServletRequest req) {
		
		long userIdx = 2;
		
		List<SharePostDto.MyPageInfo> list = sharePostService.getPostByUserId(userIdx);
		
		model.addAttribute("shareList", list);
		
		return "content/mypage/mypage_shareList :: #listContent";
	}
	
	@GetMapping("/msg")
	public String getSharePostByMessage (Model model,
			HttpServletRequest req) {
		
		long userIdx = 1;
		
		List<SharePostDto.MyPageInfo> list = messageService.getAllMessageBySender(userIdx);
		
		model.addAttribute("shareList", list);
		
		return "content/mypage/mypage_shareList :: #listContent";
	}
}
