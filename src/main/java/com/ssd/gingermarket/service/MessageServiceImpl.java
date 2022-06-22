package com.ssd.gingermarket.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
	private final MessageInfoRepository messageInfoRepository;
	private final MessageRoomRepository messageRoomRepository;
	private final UserRepository userRepository;
	
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
		
		return messageRoomRepository.findRoomIdxByPostAndSender(postIdx, senderIdx);		
	}	
	
	//쪽지 보내기 
	@Override
	@Transactional
	public void sendMessage(MessageDto.Request req, Long senderIdx, Long roomIdx) {
		MessageRoom room = messageRoomRepository.findById(roomIdx).orElseThrow();
		User sender = userRepository.findById(senderIdx).orElseThrow();
			
		MessageInfo msgInfo = MessageInfo.builder().content(req.getContent()).room(room).sender(sender).build();
			
	    messageInfoRepository.saveAndFlush(msgInfo);
	}
	
	//쪽지 내용 리스트 조회 
	@Override
	@Transactional(readOnly = true)
    public List<MessageResponse> getAllMessage(Long roomIdx) {
		List<MessageInfo> messageList = messageInfoRepository.findAllByRoomIdx(roomIdx);
		
        return messageList.stream().map(MessageResponse::new).collect(Collectors.toList());
    }
	
	//쪽지함 리스트 조회 
	@Override
	@Transactional(readOnly = true)
	public List<MessageDto.Info> getAllRoom(Long userIdx) {
		
		User author = userRepository.findById(userIdx).orElseThrow();
		User sender = userRepository.findById(userIdx).orElseThrow();
		
		List<MessageRoom> list = messageRoomRepository.findByAuthorOrSenderOrderByCreatedDateDesc(author, sender);
		System.out.println("messageRoom List : " + list.size());
		
		List<MessageDto.Info> roomList = new ArrayList<MessageDto.Info>();
		
		for(MessageRoom room : list) {
			MessageInfo m = messageInfoRepository.findTop1ByRoomOrderByCreatedDateDesc(room);
			
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
				
				room.getPost().getPostIdx(),
				room.getPost().getAuthor().getUserIdx(),
				room.getPost().getAuthor().getName(),
				(room.getPost().getAuthor().getImage() == null ? "" : "/upload/" + room.getPost().getAuthor().getImage().getUrl()),
				room.getPost().getTitle(),
					
				(room.getPost().getImage() == null ? "" : "/upload/" + room.getPost().getImage().getUrl()),
					
				room.getSender().getUserIdx(),
				room.getSender().getName(),
				(room.getSender().getImage() == null ? "" : "/upload/" + room.getSender().getImage().getUrl()),
					
				m.getContent(),
				
				isRead,
				
				m.getCreatedDate());
				
				roomList.add(info);
		}
		
		Collections.sort(roomList, new Comparator<Info>() {
			@Override
			public int compare(Info i1, Info i2) {
				return i2.getSendTime().compareTo(i1.getSendTime());
			}
		});
		
		return roomList;
	}  

	@Override
	@Transactional(readOnly = true)
	public List<SharePostDto.MyPageInfo> getAllMessageBySender(Long userIdx) {
		User sender = userRepository.findById(userIdx).orElseThrow();
		List<MessageRoom> roomList = messageRoomRepository.findAllMessageBySender(sender);
		
		List<SharePostDto.MyPageInfo> postList = roomList.stream().map(msg -> new SharePostDto.MyPageInfo(
				msg.getPost().getPostIdx(),
				(msg.getPost().getImage() == null ? "" : msg.getPost().getImage().getUrl()),
				msg.getPost().getTitle(),
				(msg.getPost().getProgress().equals("Y") ? "나눔 완료" : "진행중"),
				msg.getPost().getCreatedDate(),
				msg.getRoomIdx()))
				.collect(Collectors.toList());
		
		return postList;
	}
	
	//쪽지 읽음 처리 
	@Override
	@Transactional
	public void updateIsRead(Long senderIdx, Long roomIdx) {
	
		messageInfoRepository.updateIsRead(senderIdx, roomIdx);
			
	}  

}
