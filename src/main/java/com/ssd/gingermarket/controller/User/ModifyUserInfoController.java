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

import com.ssd.gingermarket.domain.Image;
import com.ssd.gingermarket.dto.ImageDto;
import com.ssd.gingermarket.dto.UserDto;
import com.ssd.gingermarket.service.ImageService;
import com.ssd.gingermarket.service.UserService;

import lombok.RequiredArgsConstructor;

//@Slf4j //로그 
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class ModifyUserInfoController {

	private final UserService userService;
	private final ImageService imageService;
	
	@PutMapping("/{userIdx}")
	public RedirectView updateUserInfo(@PathVariable("userIdx")Long userIdx,
			@ModelAttribute("userInfo") UserDto.Response dto) {
		
		// 프로필 사진 파일이 들어온다면
		if(!dto.getFile().getOriginalFilename().equals("")) {
			ImageDto.Request imgDto = new ImageDto.Request(dto.getFile());
			Image img = imageService.uploadFile(imgDto.getImageFile());
			userService.modifyUserImage(userIdx, img);
		}
		
		userService.modifyUser(userIdx, dto);
		
		return new RedirectView("/mypage");
	}
}
