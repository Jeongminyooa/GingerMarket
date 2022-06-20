package com.ssd.gingermarket.controller.SharePost;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssd.gingermarket.dto.SharePostDto;
import com.ssd.gingermarket.dto.SharePostDto.Request;
import com.ssd.gingermarket.service.ImageService;
import com.ssd.gingermarket.service.SharePostService;

import lombok.RequiredArgsConstructor;

//@Slf4j //로그 
@RestController 
@RequestMapping("/share-posts")
@RequiredArgsConstructor
public class ViewSharePostController {
	private final SharePostService sharePostService;
	private final ImageService imageService;

	/**
     * 게시글 상세 조회
     */
	@GetMapping("/{postIdx}")
	public ModelAndView getPost(@PathVariable Long postIdx){
		Long sessionIdx = (long)2;
		ModelAndView mav = new ModelAndView("content/sharePost/sharePost_view");
		mav.addObject("postInfo", sharePostService.getPost(postIdx));
		mav.addObject("senderIdx", sessionIdx);
		mav.addObject("sessionIdx", sessionIdx);
		return mav;
	}
	
	/**
     * 게시글 리스트 조회
     */
	@GetMapping("")
	public ModelAndView getPostList() {
		
		Long userIdx = (long) 2;
		
		ModelAndView mav = new ModelAndView("content/sharePost/sharePostList");
		mav.addObject("allPostList", sharePostService.getAllPost());
		mav.addObject("favPostList", sharePostService.getFavPost(userIdx));
		
		mav.addObject("userIdx", 1); //user session 구현 후 수정 예정 

		
		return mav;
	}
	
	
}
