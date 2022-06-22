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
		
		private Long authorIdx;
		private String authorName;
		private String authorImgUrl;
		
		private Long groupIdx;
		private Long postAuthorIdx;
		
		private String phone1;
		private String phone2;
		private String phone3;
		
		
		public Apply toEntity(GroupBuying groupBuying, User author){
			return Apply.builder()
					.message(message)
					.author(author)
					.groupBuying(groupBuying)
					.phone(phone1+phone2+phone3)
					.build();
		}
		

		public void setPhoneNumber(String phone) {
			this.phone1 = phone.substring(0, 3);
			this.phone2 = phone.substring(3, 7);
			this.phone3 = phone.substring(7, 11);
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
			this.groupIdx = apply.getGroupBuying().getGroupIdx();
			
		}
	
	}
	
	
}
