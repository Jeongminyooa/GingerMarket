package com.ssd.gingermarket.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.ssd.gingermarket.domain.Image;
import com.ssd.gingermarket.domain.SharePost;
import com.ssd.gingermarket.domain.TestEntity;
import com.ssd.gingermarket.domain.User;

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
		
		@NotBlank(message="{notBlank.title}")
		private String title;
		@NotNull(message="{notNull.category}")
		private String category;
		@Length(max=1000, message="{size.descr}")
		private String descr;
		@Length(max=255, message="{size.address}")
		private String address;
		
		private Image image;
		private String imgUrl;
		private MultipartFile file;
		
		private User author;
		private Long authorIdx;
			
		public SharePost toEntity() {
			return SharePost.builder()
					.title(title)
					.category(category)
					.descr(descr)
					.address(address)
					.image(image)
					.author(author)
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
			this.authorIdx = sharePost.getAuthor().getUserIdx();
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
		private User author;
		
		private String category;
		private String title;
		private String imgUrl;
		private String address;
		private boolean progress;
		//추후 MessageRoom 객체 참조 
		private int msgCount;
		
		public CardResponse(SharePost sharePost) {
			this.postIdx = sharePost.getPostIdx();
			this.author = sharePost.getAuthor();
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
		
		private User author;
		private String address;
		
		private String category;
		private String title;
		private String imgUrl;
		private String descr;
		private boolean progress;
		private LocalDateTime enrollDate;
		private int msgCount;
		
		public DetailResponse(SharePost sharePost) {
			this.postIdx = sharePost.getPostIdx();
			this.author = sharePost.getAuthor();
			this.address = sharePost.getAddress();
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
