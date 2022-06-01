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
	public static class Info {
		private Long postIdx;
		// 추후 User 객체참조
		private Long authorIdx;
		private String address;
		private String favItem;
		private String category;
		
		private String keyword;
		private String title;
		private Long imageIdx;
		private String descr;
		private boolean progress;
		private LocalDateTime enrollDate;
		// 추후 MessageRoom 객체 참조
		private int msgCount;
	}
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
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class cardResponse{
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
		
		public cardResponse(SharePost sharePost) {
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
	public static class viewResponse{
		private Long postIdx;
		private Long authorIdx;
		private String category;
		private String title;
		private Long imageIdx;
		private String descr;
		private boolean progress;
		private LocalDateTime enrollDate;
		private int msgCount;
		
		public viewResponse(SharePost sharePost) {
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
			this.enrollDate = sharePost.getEnrollDate();
			this.msgCount = sharePost.getMessageCnt();
		}
	}
	

}
