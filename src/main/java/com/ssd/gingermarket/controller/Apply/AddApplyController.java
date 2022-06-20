package com.ssd.gingermarket.controller.Apply;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.ssd.gingermarket.dto.ApplyDto;
import com.ssd.gingermarket.dto.GroupBuyingDto;
import com.ssd.gingermarket.service.GroupBuyingService;

import lombok.RequiredArgsConstructor;

//@Slf4j //로그
@RestController 
@RequestMapping("/group-buying")
@RequiredArgsConstructor
public class AddApplyController {

	private final GroupBuyingService groupBuyingService;


	// 공구 신청 등록 (사용자)
	@PostMapping("/{groupIdx}/apply")
	public RedirectView addApply(ApplyDto.Request apply, @PathVariable Long groupIdx) {
		
		Long userIdx = (long)2;
		
		apply.setUserIdx(userIdx);
		apply.getMessage();
		//groupBuyingService.addApply(apply, groupIdx);

		return new RedirectView("/group-buying/" + groupIdx);
	} 
	
}
	