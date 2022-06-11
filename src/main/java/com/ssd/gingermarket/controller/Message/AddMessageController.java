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
	
	private final SharePostService sharePostService;
	private final MessageService messageService;
	
//	@PostMapping("/{postIdx}")
//	public RedirectView sendMessage(@PathVariable Long postIdx, MessageDto.Request req) {	
//		Long senderIdx = (long) 1; //session구현 후 변경
//		
//		req.setSenderIdx(senderIdx);
//		messageService.sendMessage(req, postIdx);
//		
//        return new RedirectView("/messages/" + postIdx);
//    }
	
	@PostMapping("/{postIdx}")
	public int sendMessage(@PathVariable Long postIdx, @RequestBody MessageDto.Request req) {	
		Long senderIdx = (long) 1; //session구현 후 변경
		
		req.setSenderIdx(senderIdx);
		messageService.sendMessage(req, postIdx);
		
        return 1;
    }
	
}
