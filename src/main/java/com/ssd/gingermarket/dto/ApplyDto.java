package com.ssd.gingermarket.dto;

import java.util.List;

import com.ssd.gingermarket.domain.Apply;
import com.ssd.gingermarket.domain.GroupBuying;
import com.ssd.gingermarket.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ApplyDto {
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Info{
		private Long applyIdx;
		private String phone;
		private String message;
		private User author;
		private String imgUrl;
		private Long authorIdx;
		
		public Apply toEntity(){
			return Apply.builder()
					.phone(phone)
					.message(message)
					.author(author)
					.build();
		}
		
		public Info(Apply apply) {
			this.applyIdx = apply.getApplyIdx();
			this.phone = apply.getPhone();
			this.message = apply.getMessage();
			this.author = apply.getAuthor();
			this.authorIdx = apply.getAuthor().getUserIdx();
		}

	
	}
	
	
}
