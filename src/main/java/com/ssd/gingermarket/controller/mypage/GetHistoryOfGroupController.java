package com.ssd.gingermarket.controller.mypage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssd.gingermarket.dto.GroupBuyingDto;
import com.ssd.gingermarket.service.GroupBuyingService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/history")
@RequiredArgsConstructor
public class GetHistoryOfGroupController {
	
	private final GroupBuyingService groupBuyingService;
	
	@GetMapping("/group")
	public String getGroupHistory (Model model,
			HttpServletRequest req) {
		
		long userIdx = 1;
		
		List<GroupBuyingDto.MyPageInfo> list = groupBuyingService.getGroupBuyingByUserId(userIdx);
		
		// model.addAttribute("groupList", list);
		
		return "content/mypage/mypage_groupList :: #listContent";
	}
	
	@GetMapping("/apply")
	public String getSharePostByMessage (Model model,
			HttpServletRequest req) {
		
		long userIdx = 1;
		
		// List<GroupBuyingDto.MyPageInfo> list = applyService.getAllApplyByAuthor(userIdx);
		
		// model.addAttribute("groupList", list);
		
		return "content/mypage/mypage_groupList :: #listContent";
	}

}
