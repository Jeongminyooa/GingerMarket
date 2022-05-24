package com.ssd.gingermarket.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssd.gingermarket.service.ExperiodService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j //로그 
@RestController 
@RequestMapping("/experiods")
@RequiredArgsConstructor
public class ExperiodController {
	private final ExperiodService experiodService;
	
	// 카테고리 교체주기
	@ModelAttribute("categoryMap")
	public Map<String, Integer> categoryExperiodMap() {
		Map<String, Integer> category = new HashMap<String, Integer>();
		category.put("칫솔", 90);
		category.put("샤워볼", 30);
		category.put("수세미", 30);
		category.put("플라스틱 용기", 90);
		category.put("수건", 365);
		category.put("샤워필터기", 75);
		category.put("도마", 365);
		category.put("면도기", 14);
		category.put("행주", 30);
		category.put("고무장갑", 90);
		category.put("베개솜", 365);
		category.put("빗", 180);
		category.put("렌즈통", 30);
		category.put("인공눈물", 45);
		return category;
	}
	
	// 생성
	@PostMapping("")
	public String createExperiod(@RequestParam(value = "category") String category) {
		long id = 1; // test..
		
		// 카테고리에 따른 d-day
		Map<String, Integer> map = categoryExperiodMap();
		int period = map.get(category);
		
		experiodService.addExperiod(id, category, period);
		return "redirect:/mypage?category=experiod";
	}
	
	// 삭제
	@DeleteMapping("/{eid}")
	public String deleteExperiod(@PathVariable(value = "eid") Long experiodIdx) { 
		experiodService.removeExperiod(experiodIdx);
		
		return "redirect:/mypage?category=experiod";
	}
}
