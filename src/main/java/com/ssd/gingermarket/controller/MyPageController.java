package com.ssd.gingermarket.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import lombok.RequiredArgsConstructor;

//@Slf4j //로그 
@RestController 
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {
	@GetMapping("")
	public ModelAndView gomMyPage(@RequestParam(value="category", required=false) String category) {
		ModelAndView mav = new ModelAndView("content/mypage/mypage");
		
		if(category != null) {
			mav.addObject("category", category);
		}
		return mav;
	}
}
