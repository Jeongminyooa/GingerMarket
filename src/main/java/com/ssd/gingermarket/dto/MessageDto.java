package com.ssd.gingermarket.dto;

import java.time.LocalDateTime;

import com.ssd.gingermarket.domain.MessageInfo;
import com.ssd.gingermarket.domain.MessageRoom;
import com.ssd.gingermarket.domain.SharePost;
import com.ssd.gingermarket.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MessageDto {
	public static String getUploadDirPath(String imageUrl) {
		return "/upload/" + imageUrl;
	}
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class Info{

		private Long roomIdx;
		
		private Long postIdx;
		private Long authorIdx;
		private String authorName;
		private String authorImgUrl;
		private String title;
		private String postImgUrl;
		
		private Long senderIdx;
		private String senderName;
		private String senderImgUrl;
		
		private String content;
		private boolean isRead;
		private LocalDateTime sendTime;
		
	}

	@NoArgsConstructor
	@Data
	public static class Request{
		 
		private String content;
		private User sender;
		private Long senderIdx;
		private SharePost post;
		private MessageRoom room;
		private User author;
		private Long authorIdx;
		private Long roomIdx;
		
		public MessageRoom toRoomEntity() {
			return MessageRoom.builder()
					.post(post)
					.author(author)
					.sender(sender)
					.build();
		}
		
		public MessageInfo toMsgEntity() {
			return MessageInfo.builder()
					.room(room)
					.content(content)
					.sender(sender)
					.build();
		}
	}
	
	@NoArgsConstructor
	@Data
	public static class MessageResponse{
		
		private Long messageIdx;
		private String content;
		private LocalDateTime sendDate;
		private boolean isRead;
		
		private Long senderIdx;
		private String senderName;
		private String senderImgUrl;
		
		private Long authorIdx;
		private String authorName;
		private String authorImgUrl;
		
		
		public MessageResponse(MessageInfo message) {
			this.messageIdx = message.getMessageIdx();
			this.content = message.getContent();
			this.sendDate = message.getCreatedDate();
			
			boolean isRead;
			if(message.getIsRead().equals("Y"))
				isRead = true;
			else
				isRead = false;
			this.isRead = isRead;
		
			this.senderIdx = message.getSender().getUserIdx();
			this.senderName = message.getSender().getName();
			
			try {
				this.senderImgUrl = getUploadDirPath(message.getSender().getImage().getUrl());
			}catch (Exception e ) {	           
				this.senderImgUrl = "";
			}
			
			this.authorIdx = message.getRoom().getPost().getAuthor().getUserIdx();
			this.authorName = message.getRoom().getPost().getAuthor().getName();
			try {
				this.authorImgUrl = getUploadDirPath(message.getRoom().getPost().getAuthor().getImage().getUrl());
			}catch (Exception e ) {	           
				this.authorImgUrl = "";
			}
			
		}
	}

}
