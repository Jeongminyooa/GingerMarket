package com.ssd.gingermarket.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.ssd.gingermarket.dto.ExperiodDto;
import com.ssd.gingermarket.service.ExperiodService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j //로그 
@Controller 
@RequestMapping("/experiods")
@RequiredArgsConstructor
public class ExperiodController {
	private final ExperiodService experiodService;
	
	// 카테고리 교체주기
	public static Map<String, Integer> categoryExperiodMap() {
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
	public RedirectView createExperiod(@RequestParam(value = "category") String category,
			HttpServletRequest req) { 
		
//		HttpSession session = req.getSession(false);
//		Long authorIdx = (Long) session.getAttribute("userIdx");
		
		Long authorIdx = (long) 1;
		
		// 카테고리에 따른 d-day
		Map<String, Integer> map = categoryExperiodMap();
		int period = map.get(category);
		
		experiodService.addExperiod(authorIdx, category, period);
		
		return new RedirectView("/mypage?category=experiod");
	}
	
	// 삭제
	@DeleteMapping("/{eid}")
	public RedirectView deleteExperiod(@PathVariable(value = "eid") Long experiodIdx) { 
		experiodService.removeExperiod(experiodIdx);
		
		return new RedirectView("/mypage?category=experiod");
	}
	
	// 날짜별 조회
	@GetMapping("/date")
	public String getExperiodByDate (Model model, @RequestParam(value="date") String date) {
		long userId = 1;
		
		List<ExperiodDto.Info> dto = experiodService.getExperiodByDate(userId, date);
		
		model.addAttribute("experiodList", dto);
		return "content/mypage/mypage_experiod :: #experiodContent";
	}
}
