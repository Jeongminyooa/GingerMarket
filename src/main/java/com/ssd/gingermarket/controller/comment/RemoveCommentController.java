package com.ssd.gingermarket.controller.comment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssd.gingermarket.service.CommentInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j //로그 
@RestController 
@RequestMapping("/comments")
@RequiredArgsConstructor
public class RemoveCommentController {

	private final CommentInfoService commentInfoService;
	
	@DeleteMapping("/{cid}")
	public ResponseEntity<Boolean> removeComment(@PathVariable("cid") Long cid) {
		commentInfoService.removeComment(cid);
		
		return ResponseEntity.ok(true);
	}
}
