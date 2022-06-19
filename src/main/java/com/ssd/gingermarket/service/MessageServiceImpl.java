package com.ssd.gingermarket.service;


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
import com.ssd.gingermarket.dto.MessageDto;
import com.ssd.gingermarket.dto.MessageDto.MessageResponse;
import com.ssd.gingermarket.dto.MessageDto.RoomResponse;
import com.ssd.gingermarket.dto.SharePostDto;
import com.ssd.gingermarket.dto.SharePostDto.DetailResponse;
import com.ssd.gingermarket.dto.TestDto;
import com.ssd.gingermarket.repository.MessageInfoRepository;
import com.ssd.gingermarket.repository.MessageRoomRepository;
import com.ssd.gingermarket.repository.SharePostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Slf4j
@RequiredArgsConstructor
@Service
public class MessageServiceImpl<T> implements MessageService {
	private final SharePostRepository sharePostRepository;
	private final MessageInfoRepository messageRepository;
	private final MessageRoomRepository messageRoomRepository;
	
	//쪽지함 존재 확인 -> roomIdx
	@Override
	@Transactional	
	public Long existRoom(Long postIdx, Long authorIdx, Long senderIdx) {
		return messageRoomRepository.existsByIds(postIdx, authorIdx, senderIdx);
	}
	
	//쪽지함 생성  
	@Override
	@Transactional	
	public void addRoom(MessageDto.Request req, Long postIdx) {
		SharePost post = sharePostRepository.findById(postIdx).orElseThrow();
		req.setPost(post);
			
		messageRoomRepository.saveAndFlush(req.toRoomEntity());
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
	public Long sendMessage(MessageDto.Request req, Long roomIdx) {
		MessageRoom room = messageRoomRepository.findById(roomIdx).orElseThrow();
		req.setRoom(room);
		
		return messageRepository.save(req.toMsgEntity()).getRoom().getRoomIdx();
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
		
		List<MessageDto.Info> roomList = list.stream().map(msg -> new MessageDto.Info(
				msg.getRoomIdx(),
				msg.getPost().getPostIdx(),
				msg.getPost().getTitle(),
				(msg.getPost().getImage() == null ? "" : "/upload/" + msg.getPost().getImage().getUrl()),
				msg.getPost().getCreatedDate(),
				
				msg.getSenderIdx(),
				
				msg.getMessages().get(msg.getMessages().size() - 1).getContent()))
				
				.collect(Collectors.toList());
		
		return roomList;
	}  

}
