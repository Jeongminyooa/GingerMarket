package com.ssd.gingermarket.controller.GroupBuying;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.ssd.gingermarket.domain.Image;
import com.ssd.gingermarket.dto.ImageDto;
import com.ssd.gingermarket.dto.GroupBuyingDto;
import com.ssd.gingermarket.service.GroupBuyingService;
import com.ssd.gingermarket.service.ImageService;
import lombok.RequiredArgsConstructor;

@Controller 
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


	@GetMapping("/{groupIdx}/edit")
	public ModelAndView getUpdateForm(HttpServletRequest req, @PathVariable Long groupIdx) { 		
		
		HttpSession session = req.getSession();
		Long userIdx = (long)session.getAttribute("userIdx");
		
		GroupBuyingDto.Request updateReq  = groupBuyingService.getPostForModify(groupIdx);

		ModelAndView mav = new ModelAndView("content/groupBuyingPost/groupPost_update");
		mav.addObject("updateReq", updateReq);
		mav.addObject("groupIdx", groupIdx);
		mav.addObject("userIdx", userIdx);
		
		return mav;
	}

	@PutMapping("/{groupIdx}")
    public String updatePost(HttpServletRequest req, @Validated @ModelAttribute("updateReq") GroupBuyingDto.Request groupBuying, Errors error, @PathVariable Long groupIdx) 
	{
		HttpSession session = req.getSession();
		Long userIdx = (long)session.getAttribute("userIdx");
		
		if(error.hasErrors())
			return "content/groupBuyingPost/groupPost_update";
		
		if(!groupBuying.getFile().getOriginalFilename().equals("")) {
			ImageDto.Request imgDto = new ImageDto.Request(groupBuying.getFile());
			Image img = imageService.uploadFile(imgDto.getImageFile());
			groupBuying.setImage(img);
		}
		
		groupBuying.setAuthorIdx(userIdx);
		groupBuyingService.modifyPost(groupIdx, groupBuying);

        return  "redirect:/group-buying/" + groupIdx;
        		
        }


}