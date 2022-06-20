package com.ssd.gingermarket.controller.Apply;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.ssd.gingermarket.service.GroupBuyingService;

import lombok.RequiredArgsConstructor;

//@Slf4j //로그 
@RestController 
@RequestMapping("/group-buying")
@RequiredArgsConstructor
public class ViewApplyController {
	private final GroupBuyingService groupBuyingService;


	//공구 신청 조회 (작성자)
	@GetMapping("/{groupIdx}/apply-form")
	public ModelAndView getApplyList() {
		
		ModelAndView mav = new ModelAndView("content/groupBuyingPost/groupPost_apply");
		//mav.addObject("applyList", groupBuyingService.getAllApply());
		return mav;
	}
	

}