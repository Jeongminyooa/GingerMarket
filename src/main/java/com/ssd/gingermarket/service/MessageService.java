package com.ssd.gingermarket.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ssd.gingermarket.dto.MessageDto;
import com.ssd.gingermarket.dto.MessageDto.MessageResponse;
import com.ssd.gingermarket.dto.MessageDto.RoomResponse;
import com.ssd.gingermarket.dto.SharePostDto;
import com.ssd.gingermarket.dto.SharePostDto.DetailResponse;
import com.ssd.gingermarket.dto.SharePostDto.Request;

@Service
public interface MessageService {

	public Long existRoom(Long postIdx, Long authorIdx, Long senderIdx);
	//쪽지함 생성
	public Long addRoom(Long postIdx, Long senderIdx);
	
	//쪽지 보내기 
	//public Long sendMessage(MessageDto.Request req, Long roomIdx);
	
	//쪽지 리스트 조회 
	public List<MessageResponse> getAllMessage(Long roomIdx);

	//쪽지함 조회
	public List<MessageDto.Info> getAllRoom(Long userIdx);
	
	//쪽지함 상세 조회
	public Long getRoom(Long postIdx, Long senderIdx);
	
	
	public Long existRoom(Long postIdx, Long senderIdx);
	
	
	public void sendMessage(MessageDto.Request req, Long senderIdx, Long roomIdx);
	public Long sendMessage2(MessageDto.Request req, Long roomIdx);
	
	
	public void updateIsRead(Long senderIdx, Long roomIdx);
	
	
}
