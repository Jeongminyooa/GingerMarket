package com.ssd.gingermarket.controller.Apply;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssd.gingermarket.service.ApplyInfoService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/apply")
@RequiredArgsConstructor
public class ViewApplyController {
	private final ApplyInfoService applyInfoService;


	//공구 신청 조회 (작성자)
	@GetMapping("/{groupIdx}")
	public ModelAndView getApplyList(HttpServletRequest req, @PathVariable Long groupIdx) {
		HttpSession session = req.getSession();
		Long userIdx = (long)session.getAttribute("userIdx");
		
		ModelAndView mav = new ModelAndView("content/groupBuyingPost/groupPost_apply");

		mav.addObject("userIdx", userIdx);
		mav.addObject("applyList", applyInfoService.getAllApply(groupIdx));
    
		return mav;
	}
	

}