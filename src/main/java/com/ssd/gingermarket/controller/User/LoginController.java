package com.ssd.gingermarket.controller.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ssd.gingermarket.domain.User;
import com.ssd.gingermarket.dto.UserDto;
import com.ssd.gingermarket.service.UserService;

import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j 
@RestController
@RequiredArgsConstructor
public class LoginController {

	private final UserService userService;
	
	@GetMapping("/user/login")
	public ModelAndView getLoginForm() {
		ModelAndView mav = new ModelAndView("content/user/user_login");
		UserDto.LoginRequest user = new UserDto.LoginRequest();
		mav.addObject("loginReq", user);
		return mav;
	}
	
	@PostMapping("/user/login")
	public RedirectView login(HttpServletRequest req, UserDto.LoginRequest dto,
			Model model){
		User user = userService.getUser(dto.getUserId(), dto.getPassword());
		if(user == null) {
			return new RedirectView("/user/signup");
		}
		else {
			HttpSession session = req.getSession();
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userIdx", user.getUserIdx());
			return new RedirectView("/share-posts");
		}

	}


	@GetMapping("/logout")
	public RedirectView Logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return new RedirectView("/user/login");
	}
	

}
