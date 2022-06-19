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
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class Info{

		private Long roomIdx;
		private SharePost post;
		private String postImgUrl;
//		private String title;
//		private String imgUrl;
//		private LocalDateTime enrollDate;
		
		private User sender;
		
		private String content;
		
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
	public static class RoomResponse{
		private String uploadDirLocal = "/upload/";
		
		private Long idx;
		private String title;
		private String imgUrl;
		private LocalDateTime enrollDate;
		private boolean progress;
		
	}
	
	@NoArgsConstructor
	@Data
	public static class MessageResponse{
		
		private Long messageIdx;
		private String content;
		private LocalDateTime sendDate;
		private boolean isRead;
		
		private User sender;
		
		
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
		
			this.sender = message.getSender();
			
			
		}
	}

}
