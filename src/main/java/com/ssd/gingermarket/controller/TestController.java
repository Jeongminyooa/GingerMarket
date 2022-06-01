package com.ssd.gingermarket.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.beans.factory.annotation.Value;
import com.ssd.gingermarket.domain.Image;
import com.ssd.gingermarket.dto.ImageDto;
import com.ssd.gingermarket.service.ImageService;

import lombok.*;
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
}
