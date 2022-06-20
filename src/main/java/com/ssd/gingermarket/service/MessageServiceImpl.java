package com.ssd.gingermarket.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssd.gingermarket.domain.MessageInfo;
import com.ssd.gingermarket.domain.MessageRoom;
import com.ssd.gingermarket.domain.SharePost;
import com.ssd.gingermarket.domain.User;
import com.ssd.gingermarket.dto.MessageDto;
import com.ssd.gingermarket.dto.MessageDto.Info;
import com.ssd.gingermarket.dto.MessageDto.MessageResponse;
import com.ssd.gingermarket.dto.MessageDto.RoomResponse;
import com.ssd.gingermarket.dto.SharePostDto;
import com.ssd.gingermarket.dto.SharePostDto.DetailResponse;
import com.ssd.gingermarket.dto.TestDto;
import com.ssd.gingermarket.repository.MessageInfoRepository;
import com.ssd.gingermarket.repository.MessageRoomRepository;
import com.ssd.gingermarket.repository.SharePostRepository;
import com.ssd.gingermarket.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Slf4j
@RequiredArgsConstructor
@Service
public class MessageServiceImpl<T> implements MessageService {
	private final SharePostRepository sharePostRepository;
	private final MessageInfoRepository messageRepository;
	private final MessageRoomRepository messageRoomRepository;
	private final UserRepository userRepository;
	
	//쪽지함 존재 확인 -> roomIdx
	@Override
	@Transactional	
	public Long existRoom(Long postIdx, Long authorIdx, Long senderIdx) {
		return messageRoomRepository.existsByIds(postIdx, authorIdx, senderIdx);
	}
	
	//쪽지함 존재 확인 -> roomIdx
	@Override
	@Transactional	
	public Long existRoom(Long postIdx, Long senderIdx) {
		SharePost post = sharePostRepository.findById(postIdx).orElseThrow();
		User sender = userRepository.findById(senderIdx).orElseThrow();
		return messageRoomRepository.findByPostAndSender(post, sender).getRoomIdx();
	}
	
	//쪽지함 생성  
	@Override
	@Transactional	
	public Long addRoom(Long postIdx, Long senderIdx) {
		SharePost post = sharePostRepository.findById(postIdx).orElseThrow();
		User author = userRepository.findById(post.getAuthor().getUserIdx()).orElseThrow();
		User sender = userRepository.findById(senderIdx).orElseThrow();
		
		MessageRoom room = MessageRoom.builder().author(author).post(post).sender(sender).build();
			
		Long roomIdx = messageRoomRepository.saveAndFlush(room).getRoomIdx();
		
		return roomIdx;
	}
	
	//쪽지함 정보 조회   
	@Override
	@Transactional	
	public Long getRoom(Long postIdx, Long senderIdx) {
		return messageRoomRepository.findByPostIdAndSenderId(postIdx, senderIdx);		
	}	
	
	//쪽지 보내기 
		@Override
		@Transactional
		public void sendMessage(MessageDto.Request req, Long senderIdx, Long roomIdx) {
			MessageRoom room = messageRoomRepository.findById(roomIdx).orElseThrow();
			User sender = userRepository.findById(senderIdx).orElseThrow();
			
			MessageInfo msgInfo = MessageInfo.builder().content(req.getContent()).room(room).sender(sender).build();
			
		    messageRepository.saveAndFlush(msgInfo);
		}

	//쪽지 보내기 
	@Override
	@Transactional
	public Long sendMessage2(MessageDto.Request req, Long roomIdx) {
		MessageRoom room = messageRoomRepository.findById(roomIdx).orElseThrow();
		User sender = userRepository.findById(req.getSenderIdx()).orElseThrow();
		System.out.println("sender : " + sender.getUserIdx());
		req.setRoom(room);
		req.setSender(sender);
		
		return messageRepository.saveAndFlush(req.toMsgEntity()).getRoom().getRoomIdx();
	}
	
	//쪽지 리스트 조회 
	@Override
	@Transactional(readOnly = true)
    public List<MessageResponse> getAllMessage(Long roomIdx) {
//		System.out.println("roomIdx : " + roomIdx);
		List<MessageInfo> messageList = messageRepository.findAllByRoomIdx(roomIdx);
		
        return messageList.stream().map(MessageResponse::new).collect(Collectors.toList());
    }
	
	//쪽지함 리스트 조회 
	@Override
	@Transactional(readOnly = true)
	public List<MessageDto.Info> getAllRoom(Long userIdx) {
		
		
		List<MessageRoom> list = messageRoomRepository.findByAuthorIdx(userIdx, userIdx);
		List<MessageDto.Info> roomList = new ArrayList<MessageDto.Info>();
		
		for(MessageRoom room : list) {
			MessageInfo m = messageRepository.findTop1ByRoomOrderByCreatedDateDesc(room);
			
			boolean isRead;
			if(m.getSender().getUserIdx() == userIdx) {
				isRead = true;
			} else {
				if(m.getIsRead().equals("N")) {
					isRead = false;
				} else {
					isRead = true;
				}
			}
			MessageDto.Info info = new MessageDto.Info(
					room.getRoomIdx(),
					room.getPost(),
					
					(room.getPost().getImage() == null ? "" : "/upload/" + room.getPost().getImage().getUrl()),
					
					room.getSender(),
					
					room.getMessages().get(room.getMessages().size() - 1).getContent(),
					isRead
					);
			roomList.add(info);
		}
		
		
//		List<MessageDto.Info> roomList = list.stream().map(msg -> new MessageDto.Info(
//				msg.getRoomIdx(),
//				msg.getPost(),
//				
//				(msg.getPost().getImage() == null ? "" : "/upload/" + msg.getPost().getImage().getUrl()),
//				
//				msg.getSender(),
//				
//				msg.getMessages().get(msg.getMessages().size() - 1).getContent()))
//				
//				.collect(Collectors.toList());
		
		return roomList;
	}  
	
	//쪽지
	@Override
	@Transactional
	public void updateIsRead(Long senderIdx, Long roomIdx) {
		System.out.println(senderIdx + " : " + roomIdx);
	messageRepository.updateIsRead(senderIdx, roomIdx);
		
		
	}  
	
	

}
