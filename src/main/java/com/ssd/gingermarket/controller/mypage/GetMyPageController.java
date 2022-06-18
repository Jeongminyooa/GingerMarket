package com.ssd.gingermarket.controller.mypage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssd.gingermarket.controller.ExperiodController;
import com.ssd.gingermarket.dto.ExperiodDto;
import com.ssd.gingermarket.service.ExperiodService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class GetMyPageController {

	private final ExperiodService experiodService;
	
	@ModelAttribute("items")
	public List<String> categoryList(){
		List<String> category = new ArrayList<>();
		category.add("가전제품");
		category.add("욕실용품");
		category.add("위생용품");
		category.add("주방용품");
		category.add("바디/헤어");
		category.add("청소/세탁용품");
		category.add("문구");
		category.add("생활잡화");
		
		return category;
	}
	
	@GetMapping("")
	public ModelAndView getMyPage(@RequestParam(value="category", required=false) String category) {
		
		// test
		long userId = 1;
		
		ModelAndView mav = new ModelAndView("content/mypage/mypage");
		
		List<ExperiodDto.Info> dto = experiodService.getAllExperiod(userId);
		mav.addObject("experiodList", dto);
		
		if(category != null) {
			mav.addObject("category", category);
			
			if(category.equals("experiod")) {
				Map<String, Integer> categoryMap = ExperiodController.categoryExperiodMap();
				mav.addObject("categoryMap", categoryMap);
			}
		}
		return mav;
	}
	
}
