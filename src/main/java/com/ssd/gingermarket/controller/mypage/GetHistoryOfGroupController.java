package com.ssd.gingermarket.controller.mypage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssd.gingermarket.dto.GroupBuyingDto;
import com.ssd.gingermarket.service.ApplyInfoService;
import com.ssd.gingermarket.service.GroupBuyingService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/history")
@RequiredArgsConstructor
public class GetHistoryOfGroupController {
	
	private final GroupBuyingService groupBuyingService;
	private final ApplyInfoService applyInfoService;
	
	@GetMapping("/group")
	public String getGroupHistory (Model model,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
	    long userIdx = (long) session.getAttribute("userIdx");
		
		List<GroupBuyingDto.MyPageInfo> list = groupBuyingService.getGroupBuyingByUserIdx(userIdx);
		
		model.addAttribute("groupList", list);
		
		return "content/mypage/mypage_groupList :: #listContent";
	}
	
	@GetMapping("/apply")
	public String getApplyPostHistory (Model model,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
	    long userIdx = (long) session.getAttribute("userIdx");
		
		List<GroupBuyingDto.MyPageInfo> list = applyInfoService.getAllApplyByAuthor(userIdx);
		
		model.addAttribute("groupList", list);
		
		return "content/mypage/mypage_groupList :: #listContent";
	}

}
