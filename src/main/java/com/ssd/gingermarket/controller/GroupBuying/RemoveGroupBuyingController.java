package com.ssd.gingermarket.controller.GroupBuying;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import com.ssd.gingermarket.service.GroupBuyingService;

import lombok.RequiredArgsConstructor;

//@Slf4j //로그 
@RestController 
@RequestMapping("/group-buying")
@RequiredArgsConstructor
public class RemoveGroupBuyingController {
	private final GroupBuyingService groupBuyingService;

	@DeleteMapping("{groupIdx}")
	public RedirectView deletePost(@PathVariable Long groupIdx) {
		groupBuyingService.removePost(groupIdx);
        return new RedirectView("/group-buying/posts");
    }


}