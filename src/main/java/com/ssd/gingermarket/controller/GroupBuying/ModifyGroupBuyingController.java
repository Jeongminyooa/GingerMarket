package com.ssd.gingermarket.controller.GroupBuying;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ssd.gingermarket.domain.Image;
import com.ssd.gingermarket.dto.ImageDto;
import com.ssd.gingermarket.dto.GroupBuyingDto;
import com.ssd.gingermarket.service.GroupBuyingService;
import com.ssd.gingermarket.service.ImageService;

import lombok.RequiredArgsConstructor;
//@Slf4j //로그 
@RestController 
@RequestMapping("/group-buying")
@RequiredArgsConstructor
public class ModifyGroupBuyingController {
	
	private final GroupBuyingService groupBuyingService;
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


	@GetMapping("/{groupIdx}/edit-form")
	public ModelAndView getUpdateForm(@PathVariable Long groupIdx) { 		
		GroupBuyingDto.Request req = groupBuyingService.getPostForModify(groupIdx);

		ModelAndView mav = new ModelAndView("content/groupBuyingPost/groupPost_update");
		mav.addObject("updateReq", req);
		mav.addObject("groupIdx", groupIdx);

		return mav;
	}

	@PutMapping("/{groupIdx}")
    public RedirectView updatePost(GroupBuyingDto.Request groupBuying, @PathVariable Long groupIdx) 
	{
		Long authorIdx = (long) 1; //session구현 후 변경
		
		if(!groupBuying.getFile().getOriginalFilename().equals("")) {
			ImageDto.Request imgDto = new ImageDto.Request(groupBuying.getFile());
			Image img = imageService.uploadFile(imgDto.getImageFile());
			groupBuying.setImage(img);
		}
		
		groupBuying.setAuthorIdx(authorIdx);
		groupBuyingService.modifyPost(groupIdx, groupBuying);

        return new RedirectView("/group-buying/" + groupIdx);
    }


}