package com.ssd.gingermarket.controller.mypage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssd.gingermarket.controller.ExperiodController;
import com.ssd.gingermarket.dto.ExperiodDto;
import com.ssd.gingermarket.dto.GroupBuyingDto;
import com.ssd.gingermarket.dto.SharePostDto;
import com.ssd.gingermarket.dto.UserDto;
import com.ssd.gingermarket.service.ExperiodService;
import com.ssd.gingermarket.service.GroupBuyingService;
import com.ssd.gingermarket.service.SharePostService;
import com.ssd.gingermarket.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class GetMyPageController {

	private final UserService userService;
	private final ExperiodService experiodService;
	private final GroupBuyingService groupBuyingService;
	private final SharePostService sharePostService;
	
	@ModelAttribute("items")
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
	
	@ModelAttribute("userInfo")
	public UserDto.Response formBackingObject(HttpServletRequest request) 
			throws Exception  {
		// session에서 id 값 받아오기 
		long userIdx = 2;
		return userService.getUserInfo(userIdx);
	}
	
	@GetMapping("")
	public ModelAndView getMyPage(@RequestParam(value="category", required=false) String category) {
		ModelAndView mav = new ModelAndView("content/mypage/mypage");
		
		long userIdx = 2;
		
		if(category != null) {
			mav.addObject("category", category);
			
			if(category.equals("experiod")) {
				// 교체주기 섹션
				List<ExperiodDto.Info> dto = experiodService.getAllExperiod(userIdx);
				mav.addObject("experiodList", dto);
				
				Map<String, Integer> categoryMap = ExperiodController.categoryExperiodMap();
				mav.addObject("categoryMap", categoryMap);
			} 
			else if(category.equals("group")) {
				// 공동구매 섹션
				List<GroupBuyingDto.MyPageInfo> dto = groupBuyingService.getGroupBuyingByUserId(userIdx);
				mav.addObject("groupList", dto);
			} 
			else {
				// 나눔 섹션
				List<SharePostDto.MyPageInfo> dto = sharePostService.getPostByUserId(userIdx);
				mav.addObject("shareList", dto);
			}
		}
		return mav;
	}
	
}
