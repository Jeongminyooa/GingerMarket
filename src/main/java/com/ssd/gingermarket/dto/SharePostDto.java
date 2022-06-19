package com.ssd.gingermarket.dto;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.ssd.gingermarket.domain.Image;
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
		private String uploadDirLocal = "/upload/";
		
		private String title;
		private String category;
		private String descr;
		private String address;
		
		private Image image;
		private String imgUrl;
		private MultipartFile file;
		
		private Long authorIdx;
			
		public SharePost toEntity() {
			return SharePost.builder()
					.title(title)
					.category(category)
					.descr(descr)
					.address(address)
					.image(image)
					.authorIdx(authorIdx)
					.build();
		}
		
		public Request(SharePost sharePost) {
			this.title = sharePost.getTitle();
			this.category = sharePost.getCategory();
			this.descr = sharePost.getDescr();
			this.address = sharePost.getAddress();
			try {
				this.imgUrl = this.uploadDirLocal + sharePost.getImage().getUrl();
			}catch (Exception e ) {	           
				this.imgUrl = "";
			}
			this.authorIdx = sharePost.getAuthorIdx();
		}
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class CardResponse{
		//@Value("/upload/")
		private String uploadDirLocal = "/upload/";
		
		
		private Long postIdx;
		
		//추후 User 객체 참조
		private Long userIdx;
		
		private String category;
		private String title;
		private String imgUrl;
		private String address;
		private boolean progress;
		//추후 MessageRoom 객체 참조 
		private int msgCount;
		
		public CardResponse(SharePost sharePost) {
			this.postIdx = sharePost.getPostIdx();
			this.userIdx = sharePost.getAuthorIdx();
			this.category = sharePost.getCategory();
			this.title = sharePost.getTitle();
			
			try {
				this.imgUrl = this.uploadDirLocal + sharePost.getImage().getUrl();
			}catch (Exception e ) {	           
				this.imgUrl = "";
			}
			this.address = sharePost.getAddress();
			boolean prog;
			if(sharePost.getProgress().equals("Y"))
				prog = true;
			else
				prog = false;
			this.progress = prog;
			this.msgCount = sharePost.getRooms().size();
		}
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class DetailResponse{
		private String uploadDirLocal = "/upload/";
		
		private Long postIdx;
		private Long authorIdx;
		private String category;
		private String title;
		private String imgUrl;
		private String descr;
		private boolean progress;
		private LocalDateTime enrollDate;
		private int msgCount;
		
		public DetailResponse(SharePost sharePost) {
			this.postIdx = sharePost.getPostIdx();
			this.authorIdx = sharePost.getAuthorIdx();
			this.category = sharePost.getCategory();
			this.title = sharePost.getTitle();
			String url = "";
			try {
				url = this.uploadDirLocal + sharePost.getImage().getUrl();
			}catch (Exception e ) {
				url = "";
			}finally {
				this.imgUrl = url;
			}
			this.descr = sharePost.getDescr();
			boolean prog;
			if(sharePost.getProgress().equals("Y"))
				prog = true;
			else
				prog = false;
			this.progress = prog;
			this.enrollDate = sharePost.getCreatedDate();
			this.msgCount = sharePost.getRooms().size();
		}
	}
	

}
