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

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
		@Length(max=255, message="{size.title}")
		private String title;
		
		@NotBlank(message="{notBlank.category}")
		private String category;
		
		@NotBlank(message="{notBlank.recruitNum}")
		@Pattern(regexp = "[0-9]{0,64}", message="{pattern.number}")
		private String recruitNum;

		@Length(max=255, message="{size.website}")
		private String website;
		
		@NotNull(message="{notNull.endDate}")
		@FutureOrPresent(message="{futureOrPresent.endDate}")
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private LocalDate endDate;
		
		@Length(max=1000, message="{size.descr}")
		private String descr;
		
		@NotBlank(message="{notBlank.price}")
		@Pattern(regexp = "[0-9]{0,64}", message="{pattern.number}")
		private String price;
		
		private int progress;
		
		private Image image;
		private String imgUrl;
		private MultipartFile file;
		
		private User author;
		private Long authorIdx;
		
		private int participateNum;
		
		public GroupBuying toEntity(){
			return GroupBuying.builder()
					.title(title)
					.category(category)
					.recruitNum(Integer.parseInt(recruitNum))
					.website(website)
					.endDate(endDate)
					.descr(descr)
					.price(Integer.parseInt(price))
					.progress(progress)
					.image(image)
					.author(author)
					.build();
		}
		
		public Request(GroupBuying groupBuying) {
			this.title = groupBuying.getTitle();
			this.category = groupBuying.getCategory();
			this.recruitNum = String.valueOf(groupBuying.getRecruitNum());
			this.website = groupBuying.getWebsite();
			this.endDate = groupBuying.getEndDate();
			this.descr = groupBuying.getDescr();
			this.price = String.valueOf(groupBuying.getPrice());
			this.progress = groupBuying.getProgress();
			try {
				this.imgUrl = getUploadDirPath(groupBuying.getImage().getUrl());
			}catch (Exception e ) {	           
				this.imgUrl = "";
			}
			this.authorIdx = groupBuying.getAuthor().getUserIdx();
			this.participateNum = groupBuying.getParticipateNum();
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
			
			public MyPageInfo(Long groupIdx, String imageUrl, String title, int progress, int price, LocalDate endDate) {
				this.groupIdx = groupIdx;
				this.imageUrl = imageUrl;
				if(!imageUrl.equals("")) {
					this.imageUrl = getUploadDirPath(imageUrl);
				}
				this.title = title;
				this.progress = progress;
				this.price = price;
				this.endDate = endDate;
			}
		}
		 
}
	