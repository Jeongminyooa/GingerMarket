package com.ssd.gingermarket.controller.Message;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	@PostMapping("/{postIdx}/room")
	public RedirectView enterRoom(HttpServletRequest req, @PathVariable Long postIdx)
	{
		HttpSession session = req.getSession(false);
		Long userIdx = (long)session.getAttribute("userIdx");
		
		Long roomIdx;
		//처음 방 만들 때 
		if(messageService.getRoom(postIdx, userIdx) == null) {
			roomIdx = messageService.addRoom(postIdx, userIdx);
		} else { //원래 있던 방일때 
			roomIdx = messageService.getRoom(postIdx, userIdx);
		}
		
		return new RedirectView("/messages/" + postIdx + "/room/" + roomIdx);
	}
	
	@PostMapping("/{roomIdx}")
	public int sendMessage(HttpServletRequest req, @PathVariable Long roomIdx, @RequestBody MessageDto.Request msgReq) {	
		HttpSession session = req.getSession(false);
		Long userIdx = (long)session.getAttribute("userIdx");
		
		messageService.sendMessage(msgReq, userIdx, roomIdx);
		
        return 1;
    }
	
}
