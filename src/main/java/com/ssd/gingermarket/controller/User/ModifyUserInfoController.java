package com.ssd.gingermarket.controller.User;

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
@RestController 
@RequestMapping("/user")
@RequiredArgsConstructor
public class ModifyUserInfoController {

	private final UserService userService;
	
	@PutMapping("/{user-idx}")
	public RedirectView updateUserInfo(@PathVariable("user-idx")Long userIdx,
			@ModelAttribute("userInfo") UserDto.Response dto) {
		System.out.println(dto.getAddress() + dto.getName() + dto.getUserId());
		userService.modifyUser(userIdx, dto);
		
		return new RedirectView("/mypage");
	}
}
