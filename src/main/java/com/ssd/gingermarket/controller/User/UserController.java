package com.ssd.gingermarket.controller.User;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ssd.gingermarket.domain.User;
import com.ssd.gingermarket.dto.UserDto;
import com.ssd.gingermarket.service.UserService;

import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j //로그 
@RestController 
@RequestMapping("user") 
@RequiredArgsConstructor 
public class UserController {

	private final UserService userService;


	
	@GetMapping("/signup")
	public ModelAndView goSignUp() { 
		ModelAndView mav = new ModelAndView("content/user/user_signup");
		UserDto.Request user = new UserDto.Request();
		mav.addObject("userReq", user);
	
		List<String> phone1 = new ArrayList<>();
		phone1.add("010");
		phone1.add("016");
		phone1.add("017");
		phone1.add("018");
		phone1.add("019");
		mav.addObject("phone1", phone1);
		
		List<String> category = new ArrayList<>();
		category.add("가전제품");
		category.add("욕실용품");
		category.add("위생용품");
		category.add("주방용품");
		category.add("바디/헤어");
		category.add("청소/세탁용품");
		category.add("문구");
		category.add("생활잡화");
		mav.addObject("items", category);
		
		return mav;
	}
	
	@PostMapping("")
	public RedirectView register(UserDto.Request req) {
		Long idx = userService.addUser(req);
		User user = userService.getUser(idx);
		if(user.matchPassword(req.getRepeatedPassword()))
			return new RedirectView("/user/login");
		else
			return new RedirectView("/user/signup");
    }

	@GetMapping("/quit")
	public RedirectView deleteUser(HttpServletRequest req) { 
		HttpSession session = req.getSession(false);
		Long userIdx = (Long) session.getAttribute("userIdx");
		
		System.out.println("useridx "+userIdx );
		userService.removeUser(userIdx);

        if(session != null){
            session.invalidate();
        }
        
		return new RedirectView("/user/login");
	}
	
}
