package com.ssd.gingermarket.dto;

import com.ssd.gingermarket.domain.Apply;
import com.ssd.gingermarket.domain.GroupBuying;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ApplyDto {
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Request{
		private Long id;
		private String phone;
		private String message;
		private Long userIdx;
		//private User user;
		
		public Apply toEntity(){
			return Apply.builder()
					.applyIdx(id)
					.phone(phone)
					.message(message)
					.userIdx(userIdx)
					.build();
		}
		
		public Request(Apply apply) {
			this.id = apply.getApplyIdx();
			this.phone = apply.getPhone();
			this.message = apply.getMessage();
			this.userIdx = apply.getUserIdx();
		}

	
	}
	
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Response{
		private Long id;
		private String phone;
		private String message;
		private Long imageIdx;
		private Long userIdx;
		private Long groupIdx;
		private String userId;

		public Response(Apply apply) {
			this.id = apply.getApplyIdx();
			this.phone = apply.getPhone();
			this.message = apply.getMessage();
			this.imageIdx = apply.getImageIdx();
			this.userIdx = apply.getUserIdx();
			this.groupIdx = apply.getGroupBuying().getGroupIdx();
			//this.userId = apply.getUser().getUserId();
	
		}
	
	}
}
