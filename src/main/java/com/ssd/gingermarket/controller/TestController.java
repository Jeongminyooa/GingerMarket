package com.ssd.gingermarket.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssd.gingermarket.domain.Image;
import com.ssd.gingermarket.domain.User;
import com.ssd.gingermarket.dto.ExperiodDto;
import com.ssd.gingermarket.dto.ImageDto;
import com.ssd.gingermarket.dto.UserDto;
import com.ssd.gingermarket.service.ExperiodService;
import com.ssd.gingermarket.service.ImageService;

import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j //로그 
@RestController // 결과값을 JSON 형태로 변환
@RequestMapping("test") // 공통되는 url mapping
@RequiredArgsConstructor // Autowired 없이 생성자로 주입, 반드시 private final
public class TestController {
	@Value("/upload/")
	private String uploadDirLocal;

	private final ImageService imageService;
		
	@GetMapping("/image")
	public ModelAndView image() {
		ModelAndView mav = new ModelAndView("content/image_example");
		return mav;
	}
	@PostMapping("/image-upload")
	public ModelAndView imageTest(@ModelAttribute ImageDto.Request image) {
		Image img = imageService.uploadFile(image.getImageFile());
		
		ModelAndView mav = new ModelAndView("content/image_example");
		mav.addObject("fileUrl", this.uploadDirLocal + img.getUrl());
		
		return mav;
	}
	
	
	//test
	private final ExperiodService experiodService;
	
	@GetMapping("/mypage")
	public ModelAndView gomMyPageTest(@RequestParam(value="category", required=false) String category,
			HttpSession httpSession) {
		User user = (User) httpSession.getAttribute("user");
		UserDto.Response userdto = new UserDto.Response(user);
		long userId = userdto.getUserIdx();
		
		ModelAndView mav = new ModelAndView("content/user/testmypage");
		
		List<ExperiodDto.Info> dto = experiodService.getAllExperiod(userId);
		mav.addObject("experiodList", dto);
		
		mav.addObject("user1", userdto);
		
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
