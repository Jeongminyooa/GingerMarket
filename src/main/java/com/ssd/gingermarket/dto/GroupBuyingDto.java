package com.ssd.gingermarket.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.format.annotation.DateTimeFormat;
import com.ssd.gingermarket.domain.Image;
import com.ssd.gingermarket.domain.GroupBuying;
import com.ssd.gingermarket.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class GroupBuyingDto {
	public static String getUploadDirPath(String imageUrl) {
		return "/upload/" + imageUrl;
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Request{
		private String uploadDirLocal = "/upload/";
		
		@NotBlank(message="{notBlank.title}")
		private String title;
		@NotNull(message="{notNull.category}")
		private String category;
		private int recruitNum;
		private String website;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private LocalDate endDate;
		@Length(max=1000, message="{size.descr}")
		private String descr;
		private int price;
		private int progress;
		
		private Image image;
		private String imgUrl;
		private MultipartFile file;
		
		private User author;
		private Long authorIdx;
		
		public GroupBuying toEntity(){
			return GroupBuying.builder()
					.title(title)
					.category(category)
					.recruitNum(recruitNum)
					.website(website)
					.endDate(endDate)
					.descr(descr)
					.price(price)
					.progress(progress)
					.image(image)
					.author(author)
					.build();
		}
		
		public Request(GroupBuying groupBuying) {
			this.title = groupBuying.getTitle();
			this.category = groupBuying.getCategory();
			this.recruitNum = groupBuying.getRecruitNum();
			this.website = groupBuying.getWebsite();
			this.endDate = groupBuying.getEndDate();
			this.descr = groupBuying.getDescr();
			this.price = groupBuying.getPrice();
			this.progress = groupBuying.getProgress();
			try {
				this.imgUrl = getUploadDirPath(groupBuying.getImage().getUrl());
			}catch (Exception e ) {	           
				this.imgUrl = "";
			}
			this.authorIdx = groupBuying.getAuthor().getUserIdx();
		}

	}
		
		@NoArgsConstructor
		@AllArgsConstructor
		@Data
		public static class DetailResponse{
			
			private String uploadDirLocal = "/upload/";
			
			private Long groupIdx;
			
			private Long authorIdx;
			private String authorName;
			private String authorImgUrl;
			
			private String category;
			private String title;
			private String imgUrl;
			private int recruitNum;
			private int participateNum;
			private int price;
			private String website;
			private LocalDateTime createDate;
			@DateTimeFormat(pattern = "yyyy-MM-dd")
			private LocalDate endDate;
			private String descr;
			private int progress;
			private int commentCnt;
			private boolean isApply = false;
			
			public DetailResponse(GroupBuying groupBuying) {
				this.groupIdx = groupBuying.getGroupIdx();
				this.authorIdx = groupBuying.getAuthor().getUserIdx();
				this.authorName = groupBuying.getAuthor().getName();
				try {
					this.authorImgUrl = getUploadDirPath(groupBuying.getAuthor().getImage().getUrl());
				}catch (Exception e ) {	           
					this.authorImgUrl = "";
				}
				this.category = groupBuying.getCategory();
				this.title = groupBuying.getTitle();
			
				try {
					this.imgUrl = getUploadDirPath(groupBuying.getImage().getUrl());
				}catch (Exception e ) {	           
					this.imgUrl = "";
				}
				
				this.recruitNum =  groupBuying.getRecruitNum();
				this.participateNum = groupBuying.getParticipateNum();
				this.price = groupBuying.getPrice();
				this.website = groupBuying.getWebsite();
				this.createDate = groupBuying.getCreatedDate();
				this.endDate = groupBuying.getEndDate();
				this.descr = groupBuying.getDescr();
				this.progress = groupBuying.getProgress();
				this.commentCnt = groupBuying.getCommentList().size();

			}
		
		}
		
		@NoArgsConstructor
		@AllArgsConstructor
		@Data
		public static class MyPageInfo {
			// 마이페이지 제공되는 정보
			private Long groupIdx;
			private String imageUrl;
			private String title;
			private int progress;
			private int price;
			@DateTimeFormat(pattern = "yyyy-MM-dd")
			private LocalDate endDate;
		}
		 
}
	