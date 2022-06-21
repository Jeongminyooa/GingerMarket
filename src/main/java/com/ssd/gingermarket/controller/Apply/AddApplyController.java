package com.ssd.gingermarket.controller.Apply;

import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.ssd.gingermarket.dto.ApplyDto;
import com.ssd.gingermarket.service.ApplyInfoService;
import com.ssd.gingermarket.service.UserService;

import lombok.RequiredArgsConstructor;

//@Slf4j //로그
@RestController 
@RequestMapping("/group-buying")
@RequiredArgsConstructor
public class AddApplyController {

	private final ApplyInfoService applyInfoService;
	private final UserService userService;


	// 공구 신청 등록 (사용자)
	@PostMapping("/{groupIdx}/apply-form")
	public RedirectView addApply(@PathVariable Long groupIdx, @RequestBody ApplyDto.Info apply) {
		Long authorIdx = (long)5;
		
		apply.setAuthorIdx(authorIdx);
		applyInfoService.addApply(apply, groupIdx);
		return new RedirectView("/group-buying/" + groupIdx);
	} 
	
}
	