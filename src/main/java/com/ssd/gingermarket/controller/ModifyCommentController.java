package com.ssd.gingermarket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssd.gingermarket.dto.CommentDto;
import com.ssd.gingermarket.service.CommentInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j //로그 
@RestController 
@RequestMapping("/comments")
@RequiredArgsConstructor
public class ModifyCommentController {
	private final CommentInfoService commentInfoService;
	
	@PutMapping("/{cid}")
	public ResponseEntity<Boolean> updateComment(
			@RequestBody CommentDto.Request dto,
			@PathVariable(value="cid") Long commentIdx) {
		Long authorIdx = (long) 1;
	
		commentInfoService.updateComment(dto, commentIdx);
		
		return ResponseEntity.ok(true);
	}
}
