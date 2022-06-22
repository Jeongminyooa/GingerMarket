package com.ssd.gingermarket.controller.User;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ssd.gingermarket.domain.Image;
import com.ssd.gingermarket.dto.ImageDto;
import com.ssd.gingermarket.dto.UserDto;
import com.ssd.gingermarket.service.ImageService;
import com.ssd.gingermarket.service.UserService;

import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j //로그 
@RestController 
@RequestMapping("user") 
@RequiredArgsConstructor 
public class UserController {

	private final UserService userService;
	private final ImageService imageService;
	
	@GetMapping("/signup")
	public ModelAndView getSignUp() { 
		ModelAndView mav = new ModelAndView("content/user/user_signup");
		
		mav.addObject("userReq", new UserDto.Request());
		
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
	public ModelAndView register(@Validated @ModelAttribute("userReq") UserDto.Request req, 
			BindingResult error, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if(error.hasErrors())
			return new ModelAndView("content/user/user_signup");
		
		String msg="";
		
		if(userService.userIdCheck(req.getUserId())==1) {
			msg+="이미 존재하는 아이디입니다";
		}
		if(userService.nameCheck(req.getName())==1) {
			msg+="  이미 존재하는 닉네임입니다";
		}
		
		if(!req.getImageFile().getOriginalFilename().equals("")) {
			ImageDto.Request imgReq = new ImageDto.Request(req.getImageFile());
			Image img = imageService.uploadFile(imgReq.getImageFile());
			req.setImage(img);
		}

		if(!req.getPassword().equals(req.getRepeatedPassword())) {
			msg+="  비밀번호가 일치하지 않습니다.";}
		
		if(msg!="") {
			out.println("<script>alert('"+msg+"');location.replace('/user/signup');</script>");
			out.flush();
			return new ModelAndView("redirect: /user/signup");
		}
		else {
			out.println("<script>alert('회원가입 되었습니다.'); location.replace('/user/login');</script>");
			out.flush();
			userService.addUser(req);
			return new ModelAndView("redirect: /user/login");
		}
		
	}

	@DeleteMapping("/quit")
	public RedirectView quitUser(HttpServletRequest req) throws DataIntegrityViolationException { 
		
		HttpSession session = req.getSession(false);
		Long userIdx = (Long) session.getAttribute("userIdx");
		
        if(session != null){
            session.invalidate();
        }
        userService.removeUser(userIdx);
		return new RedirectView("/user/login");
	}
	
}
