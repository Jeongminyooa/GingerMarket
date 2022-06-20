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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ssd.gingermarket.domain.Image;
import com.ssd.gingermarket.dto.ImageDto;
import com.ssd.gingermarket.dto.SharePostDto;
import com.ssd.gingermarket.dto.SharePostDto.Request;
import com.ssd.gingermarket.service.ImageService;
import com.ssd.gingermarket.service.SharePostService;
import com.ssd.gingermarket.service.UserService;

import lombok.RequiredArgsConstructor;

//@Slf4j //로그 
@RestController 
@RequestMapping("/share-posts")
@RequiredArgsConstructor
public class AddSharePostController {
	
	private String uploadDirLocal;

	private final ImageService imageService;
	private final SharePostService sharePostService;
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
	
	
	@GetMapping("/new")
	public ModelAndView getAddForm() { 
		Long userIdx = (long) 2;//user session으로 추후 수정 
		
		ModelAndView mav = new ModelAndView("content/sharePost/sharePost_add");
		
		SharePostDto.Request req = new SharePostDto.Request();
		
		String addr = userService.getUser(userIdx).getAddress();
		System.out.println("addr : " + addr);

		req.setAddress(addr);
		mav.addObject("postReq", req);
		
		return mav;
	}
	
	@PostMapping("")
	public RedirectView createPost(SharePostDto.Request post) {	
		Long authorIdx = (long) 2; //session구현 후 변경
		
		if(post.getFile().getOriginalFilename().equals("")) {
			post.setImage(null);
			System.out.println("사진이 없음"); 
		}
		else {
			ImageDto.Request imgDto = new ImageDto.Request(post.getFile());
		
			Image img = imageService.uploadFile(imgDto.getImageFile());
			
			
			post.setImage(img);
		}
		
		post.setAuthorIdx(authorIdx);
		sharePostService.addPost(post);
		
        return new RedirectView("/share-posts");
    }
	
	
}
