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

public class GroupBuyingDto {
	
	public static String getUploadDirPath(String imageUrl) {
		return "/upload/" + imageUrl;
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Request{
		private String title;
		private String category;
		private int recruitNum;
		private String website;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private LocalDate endDate;
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
			private User author;
			
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
			
			public DetailResponse(GroupBuying groupBuying) {
				this.groupIdx = groupBuying.getGroupIdx();
				this.author = groupBuying.getAuthor();
				this.category = groupBuying.getCategory();
				this.title = groupBuying.getTitle();
				String url = "";
				try {
					url = getUploadDirPath(groupBuying.getImage().getUrl());
				}catch (Exception e ) {
					url = "";
				}finally {
					this.imgUrl = url;
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
	