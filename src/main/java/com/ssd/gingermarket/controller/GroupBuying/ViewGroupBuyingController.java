package com.ssd.gingermarket.controller.GroupBuying;


import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.ssd.gingermarket.service.ImageService;
import com.ssd.gingermarket.service.GroupBuyingService;

import lombok.RequiredArgsConstructor;

//@Slf4j //로그 
@RestController 
@RequestMapping("/group-buying")
@RequiredArgsConstructor
public class ViewGroupBuyingController {
	private final GroupBuyingService groupBuyingService;

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

	//전체 공구 포스트 조회
	@GetMapping("")
	public ModelAndView getPostList(@RequestParam(value="page", defaultValue="0") int page) {
		Long sessionIdx = (long)2;
		ModelAndView mav = new ModelAndView("content/groupBuyingPost/groupPostList");
		
		mav.addObject("groupBuyingList", groupBuyingService.getAllPost(page));	
		mav.addObject("sessionIdx", sessionIdx);
		mav.addObject("favPostList", groupBuyingService.getFavPost(sessionIdx));
		return mav;
	}
	
	//공구 상세 조회
	@GetMapping("/{groupIdx}")
	public ModelAndView getPost(@PathVariable Long groupIdx) {
		Long sessionIdx = (long)2;
		ModelAndView mav = new ModelAndView("content/groupBuyingPost/groupPost_view");
		mav.addObject("postInfo", groupBuyingService.getPost(groupIdx));
		mav.addObject("sessionIdx", sessionIdx);
		return mav;
	}
	
	//검색
	@GetMapping(value="/search/{option}")
	public ModelAndView getSearchList(@PathVariable("option") String option,
			@RequestParam String keyword
			, @RequestParam(value="page", defaultValue="0") int page) {
		
		ModelAndView mav = new ModelAndView("content/groupBuyingPost/groupPostList");
		
		mav.addObject("groupBuyingList", groupBuyingService.getAllPostByKeyword(keyword, page, option));
		mav.addObject("keyword", keyword);
		
		return mav;
	}

}