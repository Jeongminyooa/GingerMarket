package com.ssd.gingermarket.controller.GroupBuying;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.ssd.gingermarket.service.GroupBuyingService;

import lombok.RequiredArgsConstructor;

//@Slf4j //로그 
@RestController 
@RequestMapping("/group-buying")
@RequiredArgsConstructor
public class ViewGroupBuyingController {
	private final GroupBuyingService groupBuyingService;


	//전체 공구 포스트 조회
	@GetMapping("/posts")
	public ModelAndView goPostList() {
		
		ModelAndView mav = new ModelAndView("content/groupBuyingPost/groupPostList");
		mav.addObject("groupBuyingList", groupBuyingService.getAllPost());
		mav.addObject("userIdx", 1);
		return mav;
	}
	
	//선택한 공구 포스트 조회
	@GetMapping("{groupIdx}")
	public ModelAndView getPost(@PathVariable Long groupIdx) {
		ModelAndView mav = new ModelAndView("content/groupBuyingPost/groupPost_view");
		
		mav.addObject("postInfo", groupBuyingService.getPost(groupIdx));
			
		return mav;
	}

}