package com.ssd.gingermarket.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ssd.gingermarket.domain.GroupBuying;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class GroupBuyingDto {
	
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
		private Long imageIdx;
		private Long authorIdx;
		private int progress;

		public GroupBuying toEntity(){
			return GroupBuying.builder()
					.title(title)
					.category(category)
					.recruitNum(recruitNum)
					.website(website)
					.endDate(endDate)
					.descr(descr)
					.price(price)
					.imageIdx(imageIdx)
					.authorIdx(authorIdx)
					.progress(progress)
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
			this.imageIdx = groupBuying.getImageIdx();
			this.authorIdx = groupBuying.getAuthorIdx();
			this.progress = groupBuying.getProgress();
		}

	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class CardResponse{
		private Long groupIdx;

		// User 객체 참조
		private Long authorIdx;
		
		private String category;
		private String title;
		private Long imageIdx;
		private int price;
		private int progress;
		private String keyword;
	
		public CardResponse(GroupBuying groupBuying) {
			this.groupIdx = groupBuying.getGroupIdx();
			this.authorIdx = groupBuying.getAuthorIdx();
			this.category = groupBuying.getCategory();
			this.title = groupBuying.getTitle();
			this.imageIdx = groupBuying.getImageIdx();
			this.price = groupBuying.getPrice();
			this.progress = groupBuying.getProgress();

		}
	}
		
		@NoArgsConstructor
		@AllArgsConstructor
		@Data
		public static class DetailResponse{
					
			private Long groupIdx;

			// User 객체 참조
			private Long authorIdx;
			
			private String category;
			private String title;
			private Long imageIdx;
			private int recruitNum;
			private int participateNum;
			private int price;
			private String website;
			private LocalDateTime createDate;
			@DateTimeFormat(pattern = "yyyy-MM-dd")
			private LocalDate endDate;
			private String descr;
			private int progress;
			
			public DetailResponse(GroupBuying groupBuying) {
				this.groupIdx = groupBuying.getGroupIdx();
				this.authorIdx = groupBuying.getAuthorIdx();
				this.category = groupBuying.getCategory();
				this.title = groupBuying.getTitle();
				this.imageIdx = groupBuying.getImageIdx();
				this.recruitNum =  groupBuying.getRecruitNum();
				this.participateNum = groupBuying.getParticipateNum();
				this.price = groupBuying.getPrice();
				this.website = groupBuying.getWebsite();
				this.createDate = groupBuying.getCreatedDate();
				this.endDate = groupBuying.getEndDate();
				this.descr = groupBuying.getDescr();
				this.progress = groupBuying.getProgress();

			}
				
		
		}
		 
}
	