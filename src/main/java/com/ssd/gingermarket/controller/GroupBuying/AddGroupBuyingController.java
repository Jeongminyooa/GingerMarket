package com.ssd.gingermarket.controller.GroupBuying;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ssd.gingermarket.domain.Image;
import com.ssd.gingermarket.dto.ImageDto;
import com.ssd.gingermarket.dto.GroupBuyingDto;
import com.ssd.gingermarket.service.GroupBuyingService;
import com.ssd.gingermarket.service.ImageService;

import lombok.RequiredArgsConstructor;

//@Slf4j
@Controller 
@RequestMapping("/group-buying")
@RequiredArgsConstructor
public class AddGroupBuyingController {

	private final GroupBuyingService groupBuyingService;

	private final ImageService imageService;
	

	@ModelAttribute("categoryList")
	public List<String> categoryList(){
		List<String> category = new ArrayList<>();
		category.add("가전제품");
		category.add("욕실용품");
		category.add("위생용품");
		category.add("주방용품");
		category.add("바디/헤어");
		category.add("청소/세탁용품");
		category.add("문구");
		category.add("생활잡화");

		return category;
	}

	//공구 포스트 등록 페이지 이동
	@GetMapping("/new-add-form")
	public ModelAndView getAddForm(HttpServletRequest req, @ModelAttribute("postReq")GroupBuyingDto.Request groupBuying) {
		HttpSession session = req.getSession(false);
		Long userIdx = (long)session.getAttribute("userIdx");
		ModelAndView mav = new ModelAndView("content/groupBuyingPost/groupPost_add");
		mav.addObject("userIdx", userIdx);
		
		return mav;
	}
	
	// 공구 포스트 등록
	@PostMapping("")
	public String addPost(HttpServletRequest req, @Validated @ModelAttribute("postReq") GroupBuyingDto.Request groupBuying, Errors error) {
		
		HttpSession session = req.getSession(false);
		Long userIdx = (long)session.getAttribute("userIdx");
		
		if(error.hasErrors()) {
			return "content/groupBuyingPost/groupPost_add";
		}
		
		if(groupBuying.getFile().getOriginalFilename().equals("")) {
			groupBuying.setImage(null);
			System.out.println("사진이 없음"); 
		}
		else {
			ImageDto.Request imgDto = new ImageDto.Request(groupBuying.getFile());
		
			Image img = imageService.uploadFile(imgDto.getImageFile());
			
			groupBuying.setImage(img);
		}
		
		groupBuying.setAuthorIdx(userIdx);
		groupBuyingService.addPost(groupBuying);
		
		return "redirect:/group-buying";
				
	}
	
}
	