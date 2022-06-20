package com.ssd.gingermarket.dto;

import java.time.LocalDateTime;

import com.ssd.gingermarket.domain.SharePost;
import com.ssd.gingermarket.domain.TestEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SharePostDto {
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Request{
		private String title;
		private String category;
		private String descr;
		private String address;
		private Long imageIdx;
		private Long authorIdx;
		
		public SharePost toEntity() {
			return SharePost.builder()
					.title(title)
					.category(category)
					.descr(descr)
					.address(address)
					.imageIdx(imageIdx)
					.authorIdx(authorIdx)
					.build();
		}
		
		public Request(SharePost sharePost) {
			this.title = sharePost.getTitle();
			this.category = sharePost.getCategory();
			this.descr = sharePost.getDescr();
			this.address = sharePost.getAddress();
			this.imageIdx = sharePost.getImageIdx();
			this.authorIdx = sharePost.getAuthorIdx();
		}
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class CardResponse{
		private Long postIdx;
		
		//추후 User 객체 참조
		private Long userIdx;
		
		private String category;
		private String title;
		private Long imageIdx;
		private String address;
		private boolean progress;
		//추후 MessageRoom 객체 참조 
		private int msgCount;
		
		public CardResponse(SharePost sharePost) {
			this.postIdx = sharePost.getPostIdx();
			this.userIdx = sharePost.getAuthorIdx();
			this.category = sharePost.getCategory();
			this.title = sharePost.getTitle();
			this.imageIdx = sharePost.getImageIdx();
			this.address = sharePost.getAddress();
			boolean prog;
			if(sharePost.getProgress().equals("'Y'"))
				prog = true;
			else
				prog = false;
			this.progress = prog;
			this.msgCount = sharePost.getMessageCnt();
		}
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class DetailResponse{
		private Long postIdx;
		private Long authorIdx;
		private String category;
		private String title;
		private Long imageIdx;
		private String descr;
		private boolean progress;
		private LocalDateTime enrollDate;
		private int msgCount;
		
		public DetailResponse(SharePost sharePost) {
			this.postIdx = sharePost.getPostIdx();
			this.authorIdx = sharePost.getAuthorIdx();
			this.category = sharePost.getCategory();
			this.title = sharePost.getTitle();
			this.imageIdx = sharePost.getImageIdx();
			this.descr = sharePost.getDescr();
			boolean prog;
			if(sharePost.getProgress().equals("'Y'"))
				prog = true;
			else
				prog = false;
			this.progress = prog;
			this.enrollDate = sharePost.getCreatedDate();
			this.msgCount = sharePost.getMessageCnt();
		}
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class MyPageInfo {
		private Long postIdx;
		private String imageUrl;
		private String title;
		private String status;
		private LocalDateTime enrollDate;
	}

}
