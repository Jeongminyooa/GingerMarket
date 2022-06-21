package com.ssd.gingermarket.controller.GroupBuying;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ssd.gingermarket.domain.Image;
import com.ssd.gingermarket.dto.ImageDto;
import com.ssd.gingermarket.dto.GroupBuyingDto;
import com.ssd.gingermarket.service.GroupBuyingService;
import com.ssd.gingermarket.service.ImageService;
import com.ssd.gingermarket.service.UserService;

import lombok.RequiredArgsConstructor;

//@Slf4j
@RestController 
@RequestMapping("/group-buying")
@RequiredArgsConstructor
public class AddGroupBuyingController {

	private final GroupBuyingService groupBuyingService;
	private String uploadDirLocal;

	private final ImageService imageService;
	private final UserService userService;
	

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
	public ModelAndView getAddForm() {
		
		ModelAndView mav = new ModelAndView("content/groupBuyingPost/groupPost_add");
		mav.addObject("postReq", new GroupBuyingDto.Request());
		return mav;
	}
	
	// 공구 포스트 등록
	@PostMapping("")
	public RedirectView addPost(GroupBuyingDto.Request groupBuying) {
		
		Long authorIdx = (long)2;
		
		if(groupBuying.getFile().getOriginalFilename().equals("")) {
			groupBuying.setImage(null);
			System.out.println("사진이 없음"); 
		}
		else {
			ImageDto.Request imgDto = new ImageDto.Request(groupBuying.getFile());
		
			Image img = imageService.uploadFile(imgDto.getImageFile());
			
			groupBuying.setImage(img);
		}
		
		groupBuying.setAuthorIdx(authorIdx);
		groupBuyingService.addPost(groupBuying);
		
		return new RedirectView("/group-buying");
	}
	
}
	