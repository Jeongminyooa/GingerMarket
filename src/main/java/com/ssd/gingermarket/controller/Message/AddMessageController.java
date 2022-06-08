package com.ssd.gingermarket.controller.Message;

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
@RequestMapping("/message")
@RequiredArgsConstructor
public class AddMessageController {
	
	private final SharePostService sharePostService;
	
	
	@GetMapping("/{postIdx}/room")
	public ModelAndView goMessageRoom(@PathVariable Long postIdx) { 
		Long senderIdx = (long) 2;//user session으로 추후 수정 
		
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
