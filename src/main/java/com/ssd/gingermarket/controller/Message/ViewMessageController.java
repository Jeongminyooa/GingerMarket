package com.ssd.gingermarket.controller.Message;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssd.gingermarket.dto.MessageDto;
import com.ssd.gingermarket.dto.SharePostDto;
import com.ssd.gingermarket.dto.SharePostDto.Request;
import com.ssd.gingermarket.service.ImageService;
import com.ssd.gingermarket.service.MessageService;
import com.ssd.gingermarket.service.SharePostService;

import lombok.RequiredArgsConstructor;

//@Slf4j //로그 
@Controller 
@RequestMapping("/messages")
@RequiredArgsConstructor
public class ViewMessageController {
	private final SharePostService sharePostService;
	private final MessageService messageService;
	
	//해당 쪽지방으로 가기 	
	@GetMapping("/{postIdx}/sender/{senderIdx}")
	public ModelAndView getRoom(@PathVariable Long postIdx, @PathVariable Long senderIdx){
		ModelAndView mav = new ModelAndView("content/message/messageInfo");
	
		mav.addObject("postIdx", postIdx);
		mav.addObject("userIdx", senderIdx); //fragment 용 
		mav.addObject("senderIdx", senderIdx);

		mav.addObject("postInfo", sharePostService.getPost(postIdx));
		
		mav.addObject("msgReq", new MessageDto.Request());
		
		//room 찾아오기
		Long roomIdx; 
		if(messageService.getRoom(postIdx, senderIdx) == null) {
			roomIdx = (long) 0;
		}
		else
			roomIdx = messageService.getRoom(postIdx, senderIdx);
		
		mav.addObject("roomIdx", roomIdx); 
		
		return mav;
	}
	
	//쪽지함리스트로 가기 
	@GetMapping("/{userIdx}/list")
	public ModelAndView getRoomList(@PathVariable Long userIdx){
		ModelAndView mav = new ModelAndView("content/message/messageList");
		
		userIdx = (long) 3; //현재 세션의 유저 
		

		List<MessageDto.Info> roomList = messageService.getAllRoom(userIdx);
		mav.addObject("roomList", roomList)	;
		mav.addObject("userIdx", userIdx);
	
		return mav;
	}
	

	//쪽지 상세 조회
	@GetMapping("/{roomIdx}")
	public String getMessages(@PathVariable Long roomIdx, Model model){
				
		Long sessionIdx = (long) 2; //현재 세션의 유저 
		
		List<MessageDto.MessageResponse> msgList = messageService.getAllMessage(roomIdx);
		
		model.addAttribute("msgList", msgList);
		model.addAttribute("userIdx", sessionIdx);
		model.addAttribute("roomIdx", roomIdx);
		model.addAttribute("senderIdx", sessionIdx);
		
		return "content/message/messageInfo :: #msg-container";
			
	}
	
	
}