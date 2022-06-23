package com.ssd.gingermarket.controller.User;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssd.gingermarket.domain.Image;
import com.ssd.gingermarket.dto.ImageDto;
import com.ssd.gingermarket.dto.UserDto;
import com.ssd.gingermarket.service.ImageService;
import com.ssd.gingermarket.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("user") 
@RequiredArgsConstructor 
public class UserController {

	private final UserService userService;
	private final ImageService imageService;
	
	@ModelAttribute("items")
	public List<String> categoryList(){
		List<String> category = new ArrayList<>();
		category.add("가전제품");
		category.add("욕실용품");
		category.add("위생용품");
		category.add("주방용품");
		category.add("바디/헤어");
		category.add("청소/세탁용품");
		category.add("문구");
		category.add("생활잡화");
		return category;
	}
	
	@ModelAttribute("phone1")
	public List<String> phone1List() {
		List<String> phone1 = new ArrayList<>();
		phone1.add("010");
		phone1.add("016");
		phone1.add("017");
		phone1.add("018");
		phone1.add("019");
		return phone1;
	}
	
	@GetMapping("/signup")
	public ModelAndView getSignUp() { 
		ModelAndView mav = new ModelAndView("content/user/user_signup");
		mav.addObject("userReq", new UserDto.Request());

		return mav;
	}

	
	@PostMapping("/checkId")
	public String checkId(@RequestParam("id") String userId) throws Exception{ 
		String text;
		
		if(userService.userIdCheck(userId)==1) 
			text="이미 존재하는 아이디입니다";
		else if(userId.length() <5 || userId.length()>10)
			text= "아이디 길이는 5이상 10이하여야 합니다.";
		else
			text= "사용가능한 아이디입니다.";
	    return text;
	    	
	}
	
	@PostMapping("/checkName")
	public String checkName(@RequestParam("name") String name) throws Exception{ 
		String text;
		
		if(userService.nameCheck(name)==1) 
			text="이미 존재하는 닉네임입니다";
		else if(name.length() >10)
			text= "닉네임 길이는 10이하여야 합니다.";
		else
			text= "사용가능한 닉네임입니다.";
	    return text;
	    	
	}
	
	@PostMapping("")
	public ModelAndView register(@Validated @ModelAttribute("userReq") UserDto.Request req, 
			BindingResult error, HttpServletResponse response) 
					throws Exception {
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(error.hasErrors())
			return new ModelAndView("content/user/user_signup");
		
		String msg="";
		
		if(userService.userIdCheck(req.getUserId())==1 || userService.nameCheck(req.getName())==1) 
			msg+="아이디와 닉네임은 중복확인해주세요";

		
		if(!req.getFile().getOriginalFilename().equals("")) {
			ImageDto.Request imgReq = new ImageDto.Request(req.getFile());
			Image img = imageService.uploadFile(imgReq.getImageFile());
			req.setImage(img);
		}

		if(!req.getPassword().equals(req.getRepeatedPassword())) {
					msg+="  비밀번호가 일치하지 않습니다.";
		}
		if(msg!="") {
			out.println("<script>alert('"+msg+"');location.replace('/user/signup');</script>");
			out.flush();
			return new ModelAndView("redirect: /user/signup");
		}
		out.println("<script>alert('회원가입 되었습니다.'); location.replace('/user/login');</script>");
		out.flush();
		userService.addUser(req);
		return new ModelAndView("redirect: /user/login");
		
		
	}


	@DeleteMapping("/quit")
	public void quitUser(HttpServletRequest req, HttpServletResponse response) throws Exception { 
		response.setContentType("text/html; charset=utf-8");
    	PrintWriter out = response.getWriter();
		HttpSession session = req.getSession(false);
		Long userIdx = (Long) session.getAttribute("userIdx");
		
        userService.removeUser(userIdx);
        //세션 삭제
        if(session != null){
            session.invalidate();
        }
        out.println("<script>alert('회원정보가 삭제되었습니다.'); location.replace('/user/login');</script>");
        out.flush();
	}

}

