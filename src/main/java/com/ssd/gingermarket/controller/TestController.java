package com.ssd.gingermarket.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssd.gingermarket.dto.TestDto;
import com.ssd.gingermarket.service.TestService;

import lombok.*;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j //로그 
@RestController // 결과값을 JSON 형태로 변환
@RequestMapping("test") // 공통되는 url mapping
@RequiredArgsConstructor // Autowired 없이 생성자로 주입, 반드시 private final
public class TestController {
	private final TestService testService;

	@GetMapping("/home")
	public ModelAndView goHome(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("content/content_container");
		return mav;
	}
  
	// postList view 확인용
	@GetMapping("/home/postList")
	public ModelAndView goPostList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("content/postList");
		return mav;
	}

	@PostMapping("/post")
	public Integer save(@RequestBody TestDto request) {
		return testService.insertTest(request);
	}

	@GetMapping("/home/viewSharePost")
	public ModelAndView goViewSharePost(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("content/sharePost/sharePost_view");
		return mav;
	}

	// share post add view 확인용
	@GetMapping("/share/addPost")
	public ModelAndView goAddPost(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("content/sharePost/sharePost_add");
		return mav;
	}

	// share post List 확인용
	@GetMapping("/share/postList")
	public ModelAndView goSharePostList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("content/sharePost/sharePostList");
		return mav;
	}
	


	@GetMapping("/groupBuying/viewGroupPost")
 	public ModelAndView goViewGroupPost(HttpServletRequest request) {
	  ModelAndView mav = new ModelAndView("content/groupBuyingPost/viewGroupPost");
	  return mav;
	}
	
	@GetMapping("/groupBuying/groupPostList")
	  public ModelAndView goGroupPostList(HttpServletRequest request) {
	  	ModelAndView mav = new ModelAndView("content/groupBuyingPost/groupPostList");
	    return mav;
	 }
	    
	@GetMapping("/groupBuying/addGroupPost")
	 public ModelAndView goAddGroupPost(HttpServletRequest request) {
	   ModelAndView mav = new ModelAndView("content/groupBuyingPost/groupPost_add");
	   return mav;
	 }
	    
	@GetMapping("/groupBuying/applyGroupPost")
	 public ModelAndView goApplyGroupPost(HttpServletRequest request) {
	   ModelAndView mav = new ModelAndView("content/groupBuyingPost/GroupPost_apply");
	   return mav;
	}

	//message List 확인용
	@GetMapping("/messages")
	public ModelAndView goMessageList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("content/message/messageList");
		return mav;
	}
	
	//message 상세조회용
		@GetMapping("/messages/roomIdx")
		public ModelAndView goMessages(HttpServletRequest request) {
			ModelAndView mav = new ModelAndView("content/message/messageInfo");
			return mav;
		}
}
