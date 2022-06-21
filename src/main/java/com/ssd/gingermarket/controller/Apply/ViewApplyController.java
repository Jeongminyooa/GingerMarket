package com.ssd.gingermarket.controller.Apply;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssd.gingermarket.service.ApplyInfoService;

import lombok.RequiredArgsConstructor;

//@Slf4j //로그 
@RestController 
@RequestMapping("/group-buying")
@RequiredArgsConstructor
public class ViewApplyController {
	private final ApplyInfoService applyInfoService;


	//공구 신청 조회 (작성자)
	@GetMapping("/{groupIdx}/apply")
	public ModelAndView getApplyList(@PathVariable Long groupIdx) {
	
		ModelAndView mav = new ModelAndView("content/groupBuyingPost/groupPost_apply");
		mav.addObject("authorIdx", 2);
		mav.addObject("groupIdx", groupIdx);
		mav.addObject("applyList", applyInfoService.getAllApply(groupIdx));
		return mav;
	}
	

}