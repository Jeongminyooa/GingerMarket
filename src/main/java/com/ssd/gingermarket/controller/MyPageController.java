package com.ssd.gingermarket.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssd.gingermarket.dto.ExperiodDto;
import com.ssd.gingermarket.service.ExperiodService;

import lombok.RequiredArgsConstructor;

//@Slf4j //로그 
@RestController 
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {
	
	private final ExperiodService experiodService;
	
	@GetMapping("")
	public ModelAndView gomMyPage(@RequestParam(value="category", required=false) String category) {
		
		// test
		long userId = 1;
		
		ModelAndView mav = new ModelAndView("content/mypage/mypage");
		
		if(category != null) {
			mav.addObject("category", category);
			
			if(category.equals("experiod")) {
				List<ExperiodDto.Info> dto = experiodService.getAllExperiod(userId);
				Map<String, Integer> categoryMap = ExperiodController.categoryExperiodMap();
				mav.addObject("experiodList", dto);
				mav.addObject("categoryMap", categoryMap);
			}
		}
		return mav;
	}
}
