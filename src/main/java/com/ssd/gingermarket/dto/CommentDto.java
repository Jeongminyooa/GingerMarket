package com.ssd.gingermarket.dto;

import java.time.LocalDateTime;

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
		private LocalDateTime createDate;
		private LocalDateTime updateDate;
		private boolean isDeleted;
		private Long parentIdx;
		private Long groupIdx;
		private Long authorIdx;
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Request {
		private String content;
		private Long parent_idx;
	}
}
