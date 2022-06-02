package com.ssd.gingermarket.controller.SharePost;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ssd.gingermarket.dto.SharePostDto;
import com.ssd.gingermarket.service.SharePostService;

import lombok.RequiredArgsConstructor;

//@Slf4j //로그 
@RestController 
@RequestMapping("/share")
@RequiredArgsConstructor
public class AddSharePostController {
	
	private final SharePostService sharePostService;
	
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
	
	
	@GetMapping("/addForm")
	public ModelAndView goAddForm() { 
		long userIdx = 2;//user session으로 추후 수정 
		
		ModelAndView mav = new ModelAndView("content/sharePost/sharePost_add");
		mav.addObject("postReq", new SharePostDto.Request());
		
		return mav;
	}
	
	@PostMapping("")
	public RedirectView createPost(SharePostDto.Request post) {	
		Long authorIdx = (long) 1; //session구현 후 변경
		
		post.setAuthorIdx(authorIdx);
		sharePostService.addPost(post);
		
        return new RedirectView("/share/posts");
    }
	
	
}
