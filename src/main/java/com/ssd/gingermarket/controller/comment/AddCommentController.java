package com.ssd.gingermarket.controller.comment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class AddCommentController {
	private final CommentInfoService commentInfoService;
	
	/**
	 * 댓글(부모) 작성
	 * @param dto
	 * @param groupIdx
	 * @return
	 */
	@PostMapping("/{gid}")
	public ResponseEntity<Boolean> addComment (
			@RequestBody CommentDto.Request dto,
			@PathVariable(value="gid") Long groupIdx) {
		Long authorIdx = (long) 2;
	
		commentInfoService.addComment(dto, authorIdx, groupIdx);
		
		return ResponseEntity.ok(true);
	}
	
	/**
	 * 대댓글 작성
	 * @param dto
	 * @param groupIdx
	 * @param parentIdx
	 * @return
	 */
	@PostMapping("/{gid}/reply/{pid}")
	public ResponseEntity<Boolean> addChildComment(
		@RequestBody CommentDto.Request dto,
		@PathVariable(value="gid") Long groupIdx,
		@PathVariable(value="pid") Long parentIdx) {
		
		Long authorIdx = (long) 2;
		
		commentInfoService.addChildComment(dto, authorIdx, groupIdx, parentIdx);
		
		return ResponseEntity.ok(true);
	}
}
