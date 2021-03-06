package com.ssd.gingermarket.controller.GroupBuying;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.ssd.gingermarket.dto.ApplyDto;
import com.ssd.gingermarket.dto.UserDto;
import com.ssd.gingermarket.service.GroupBuyingService;
import com.ssd.gingermarket.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/group-buying")
@RequiredArgsConstructor
public class ViewGroupBuyingController {
	private final GroupBuyingService groupBuyingService;
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
	
	//공구 신청 번호 선택
	@ModelAttribute("phone1")
	public List<String> phone1List() {
		List<String> phone1 = new ArrayList<>();
		phone1.add("010");
		phone1.add("016");
		phone1.add("017");
		phone1.add("018");
		phone1.add("019");
		return phone1;
	}
	

	//공구 포스트 리스트 조회
	@GetMapping("")
	public ModelAndView getPostList(HttpServletRequest req, @RequestParam(value="page", defaultValue="0") int page) {
		HttpSession session = req.getSession(false);
		Long userIdx = (long)session.getAttribute("userIdx");
		
		ModelAndView mav = new ModelAndView("content/groupBuyingPost/groupPostList");
		mav.addObject("groupBuyingList", groupBuyingService.getAllPost(page));	
		mav.addObject("favPostList", groupBuyingService.getFavPost(userIdx));
		
		return mav;
	}
	
	
	//공구 상세 조회
	@GetMapping("/{groupIdx}")
	public ModelAndView getPost(HttpServletRequest req, @PathVariable Long groupIdx) {
		HttpSession session = req.getSession(false);
		Long userIdx = (long)session.getAttribute("userIdx");
		
		ModelAndView mav = new ModelAndView("content/groupBuyingPost/groupPost_view");
		mav.addObject("postInfo", groupBuyingService.getPost(groupIdx, userIdx));
		
		UserDto.Response user = userService.getUserInfo(userIdx);
		
		ApplyDto.Info dto = new  ApplyDto.Info();
		dto.setPhone2(user.getPhone2());
		dto.setPhone3(user.getPhone3());
		
		mav.addObject("applyInfo", dto);
		mav.addObject("userIdx", userIdx);
				
		return mav;
	}
	
	//키워드 검색(제목, 카테고리)
	@GetMapping(value="/search/{option}")
	public ModelAndView getSearchList(HttpServletRequest req, @PathVariable("option") String option,
			@RequestParam String keyword,
			@RequestParam(value="page", defaultValue="0") int page) {
		
		HttpSession session = req.getSession(false);
		Long userIdx = (long)session.getAttribute("userIdx");
		
		ModelAndView mav = new ModelAndView("content/groupBuyingPost/groupPostList");
		List<String> check = new ArrayList<String>();
		mav.addObject("groupBuyingList", groupBuyingService.getAllPostByKeyword(keyword, page, option));
		mav.addObject("favPostList", check);
		mav.addObject("keyword", keyword);
		mav.addObject("userIdx", userIdx);
		
		return mav;
	}

}