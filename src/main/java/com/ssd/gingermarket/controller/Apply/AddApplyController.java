package com.ssd.gingermarket.controller.Apply;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.ssd.gingermarket.dto.ApplyDto;
import com.ssd.gingermarket.service.ApplyInfoService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/group-buying")
@RequiredArgsConstructor
public class AddApplyController {

	private final ApplyInfoService applyInfoService;

	// 공구 신청 등록 (사용자)
	@PostMapping("/{groupIdx}/apply-form")
	public RedirectView addApply(HttpServletRequest req,
			@PathVariable("groupIdx") Long groupIdx,
			@ModelAttribute("applyInfo") ApplyDto.Info apply) {
		
		HttpSession session = req.getSession(false);
		Long userIdx = (long)session.getAttribute("userIdx");
				
		applyInfoService.addApply(apply, userIdx, groupIdx);
		return new RedirectView("/group-buying/" + groupIdx);
	} 
}
	