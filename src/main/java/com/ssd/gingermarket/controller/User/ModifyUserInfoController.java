package com.ssd.gingermarket.controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.ssd.gingermarket.dto.UserDto;
import com.ssd.gingermarket.service.UserService;

import lombok.RequiredArgsConstructor;

//@Slf4j //로그 
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class ModifyUserInfoController {

	private final UserService userService;
	
	@PutMapping("/{user-idx}")
	public String updateUserInfo(@PathVariable("user-idx")Long userIdx,
			@Validated @ModelAttribute("userInfo") UserDto.Response dto,
			Errors error) {
		if(error.hasErrors()) {
			return "content/mypage/mypage";
		}
		userService.modifyUser(userIdx, dto);
		
		return "content/mypage/mypage";
	}
}
