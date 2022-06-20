package com.ssd.gingermarket.controller.Message;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ssd.gingermarket.domain.SharePost;
import com.ssd.gingermarket.dto.MessageDto;
import com.ssd.gingermarket.dto.MessageDto.Request;
import com.ssd.gingermarket.dto.SharePostDto;
import com.ssd.gingermarket.service.MessageService;
import com.ssd.gingermarket.service.SharePostService;

import lombok.RequiredArgsConstructor;

//@Slf4j //로그 
@RestController 
@RequestMapping("/messages")
@RequiredArgsConstructor
public class AddMessageController {
	
	private final MessageService messageService;
	
	@PostMapping("/{postIdx}")
	public Long sendMessage(@PathVariable Long postIdx, @RequestBody MessageDto.Request req) {	
		Long sessionIdx = (long) 2; //session구현 후 변경
		
		Long authorIdx = req.getAuthorIdx();
		Long senderIdx = sessionIdx;
		
		//보내는 이가 게시글 작성자라면 DB상 sender을 넣어주기 -> 방 존재 여부 확인 위함 
		if(senderIdx == authorIdx) {
			senderIdx = req.getSenderIdx(); 
			
		}
		
		//room 존재여부 확인 
		Long roomIdx; 
		if(messageService.existRoom(postIdx, authorIdx, senderIdx) == null) {
			roomIdx = (long) 0;
		}
		else
			roomIdx = messageService.existRoom(postIdx, authorIdx, senderIdx);
		
	
		// 방이 없을 때, 처음 보내는 것일 때 
		if(roomIdx == (long)0) { 
			req.setAuthorIdx(authorIdx);
			req.setSenderIdx(senderIdx);
			messageService.addRoom(req, postIdx);
		}
		
		req.setSenderIdx(sessionIdx);
		Long existRoomIdx = messageService.existRoom(postIdx, authorIdx, senderIdx);

		Long result = messageService.sendMessage(req, existRoomIdx); //roomIdx 반환
		
        return result;
    }
	
}
