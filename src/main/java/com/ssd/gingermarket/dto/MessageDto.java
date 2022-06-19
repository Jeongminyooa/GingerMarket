package com.ssd.gingermarket.dto;

import java.time.LocalDateTime;

import com.ssd.gingermarket.domain.MessageInfo;
import com.ssd.gingermarket.domain.MessageRoom;
import com.ssd.gingermarket.domain.SharePost;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MessageDto {
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class Info{
		//private String uploadDirLocal = "/upload/";
		
		private Long roomIdx;
		private Long postIdx;
		private String title;
		private String imgUrl;
		private LocalDateTime enrollDate;
		
		private Long senderIdx;
		
		private String content;
		
	}

	@NoArgsConstructor
	@Data
	public static class Request{
		 
		private String content;
		private Long senderIdx;
		private SharePost post;
		private MessageRoom room;
		private Long authorIdx;
		private Long roomIdx;
		
		public MessageRoom toRoomEntity() {
			return MessageRoom.builder()
					.post(post)
					.authorIdx(authorIdx)
					.senderIdx(senderIdx)
					.build();
		}
		
		public MessageInfo toMsgEntity() {
			return MessageInfo.builder()
					.room(room)
					.content(content)
					.senderIdx(senderIdx)
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
		
		private Long senderIdx;
		
		
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
		
			this.senderIdx = message.getSenderIdx();
			
			
		}
	}

}
