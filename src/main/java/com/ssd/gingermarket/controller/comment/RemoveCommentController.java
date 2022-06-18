package com.ssd.gingermarket.controller.comment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.ssd.gingermarket.service.CommentInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j //로그 
@Controller 
@RequestMapping("/comments")
@RequiredArgsConstructor
public class RemoveCommentController {

	private final CommentInfoService commentInfoService;
	
	@DeleteMapping("/{cid}/{gid}")
	public RedirectView removeComment(@PathVariable("cid") Long cid
			,@PathVariable("gid") Long gid) {
		commentInfoService.removeComment(cid);
		
		return new RedirectView("/group-buying/" + gid);
	}
}
