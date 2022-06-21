package com.ssd.gingermarket.controller.SharePost;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ssd.gingermarket.domain.Image;
import com.ssd.gingermarket.dto.ImageDto;
import com.ssd.gingermarket.dto.SharePostDto;
import com.ssd.gingermarket.dto.SharePostDto.Request;
import com.ssd.gingermarket.service.ImageService;
import com.ssd.gingermarket.service.SharePostService;

import lombok.RequiredArgsConstructor;

//@Slf4j //로그 
@Controller 
@RequestMapping("/share-posts")
@RequiredArgsConstructor
public class ModifySharePostController {
	
	private final SharePostService sharePostService;
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
	
	
	@GetMapping("/{postIdx}/edit")
	public ModelAndView getUpdateForm(@PathVariable Long postIdx) { 
		Long userIdx = (long) 2;//user session으로 추후 수정 
		
		System.out.println("postIdx : " + postIdx);
		SharePostDto.Request req = sharePostService.getPostForModify(postIdx);
		
		ModelAndView mav = new ModelAndView("content/sharePost/sharePost_update");
		mav.addObject("updateReq", req);
		mav.addObject("postIdx", postIdx);
		
		mav.addObject("userIdx", 1);//session 
		
		return mav;
	}
	
	@PutMapping("/{postIdx}")
    public String updatePost(@Validated @ModelAttribute("updateReq")SharePostDto.Request post,Errors error, @PathVariable Long postIdx) 
	{
		Long authorIdx = (long) 2; //session구현 후 변경
		if(error.hasErrors())
			return "content/sharePost/sharePost_update";
			
		if(!post.getFile().getOriginalFilename().equals("")) {
			ImageDto.Request imgDto = new ImageDto.Request(post.getFile());
			Image img = imageService.uploadFile(imgDto.getImageFile());
			post.setImage(img);
		}
			
		post.setAuthorIdx(authorIdx);
	
		sharePostService.modifyPost(postIdx, post);
		
        return "redirect:/share-posts/" + postIdx;
    }
	
	@PutMapping("/{postIdx}/progress")
	public RedirectView updateProgress(@PathVariable Long postIdx)
	{
		boolean prog = sharePostService.getPost(postIdx).isProgress();
		sharePostService.modifyProgress(postIdx, prog);
		
		return new RedirectView("/share-posts/" + postIdx);
	}
	
	
}
