package com.ssd.gingermarket.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CommentDto {

	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Info {
		private Long commentIdx;
		private String content;
		private LocalDateTime createdDate;
		private LocalDateTime lastModifiedDate;
		private boolean isDeleted;
		
		private Long authorIdx; // 사용자 idx
		private String imageUrl; // 사용자 프로필 사진
		private String nickname; // 사용자 닉네임
		
		private List<CommentDto.ChildInfo> childCommentList;
		
		private boolean isPostAuthor; //작성자 여부
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class ChildInfo {
		private Long commentIdx;
		private Long parentIdx;
		private String content;
		private LocalDateTime createdDate;
		private LocalDateTime lastModifiedDate;
		private boolean isDeleted;
		
		private Long authorIdx; // 사용자 idx
		private String imageUrl; // 사용자 프로필 사진
		private String nickname; // 사용자 닉네임
		
		private boolean isPostAuthor; //작성자 여부
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Request {
		private String content;
	}
}
