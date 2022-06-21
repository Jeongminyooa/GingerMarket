package com.ssd.gingermarket.controller.User;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ssd.gingermarket.dto.UserDto;
import com.ssd.gingermarket.service.UserService;

import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j 
@RestController
@RequiredArgsConstructor
public class LoginController{

	private final UserService userService;

	@GetMapping("/user/login")
	public ModelAndView getLoginForm() {
		ModelAndView mav = new ModelAndView("content/user/user_login");
		UserDto.LoginRequest user = new UserDto.LoginRequest();
		mav.addObject("loginReq", user);
		return mav;
	}

	@PostMapping("/user/login")
	public void login(HttpServletRequest req, HttpServletResponse response, 
			UserDto.LoginRequest dto) throws Exception {
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		UserDto.Info user = userService.getUser(dto.getUserId(), dto.getPassword());
		
		if(user.getUserIdx() == null) 
			out.println("<script>alert('일치하는 회원정보가 존재하지 않습니다.');  location.replace('/user/signup'); </script>");

		else {
			HttpSession session = req.getSession();
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userIdx", user.getUserIdx());
			session.setAttribute("image", user.getImageIdx());
			out.println("<script>alert('로그인되었습니다. '); location.replace('/share-posts');</script>");
		}
		out.flush();
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
