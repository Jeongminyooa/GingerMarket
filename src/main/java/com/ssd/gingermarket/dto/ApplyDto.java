package com.ssd.gingermarket.dto;

import com.ssd.gingermarket.domain.Apply;
import com.ssd.gingermarket.domain.GroupBuying;
import com.ssd.gingermarket.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ApplyDto {
	public static String getUploadDirPath(String imageUrl) {
		return "/upload/" + imageUrl;
	}
	
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Info{
		private Long applyIdx;
		private String phone;
		private String message;
		
		private User author;
		private Long authorIdx;
		private String authorName;
		private String authorImgUrl;
		
		private GroupBuying groupBuying;
		private Long groupIdx;
		private Long postAuthorIdx;
		
		public Apply toEntity(){
			return Apply.builder()
					.phone(phone)
					.message(message)
					.author(author)
					.groupBuying(groupBuying)
					.build();
		}
		
		public Info(Apply apply) {
			this.applyIdx = apply.getApplyIdx();
			this.phone = apply.getPhone();
			this.message = apply.getMessage();
			this.authorIdx = apply.getAuthor().getUserIdx();
			this.authorName = apply.getAuthor().getName();
			try {
				this.authorImgUrl = getUploadDirPath(apply.getAuthor().getImage().getUrl());
			}catch (Exception e ) {	           
				this.authorImgUrl = "";
			}
			this.postAuthorIdx = apply.getGroupBuying().getAuthor().getUserIdx();
			this.groupIdx = apply.getGroupBuying().getGroupIdx();
		}
	
	}
	
	
}
