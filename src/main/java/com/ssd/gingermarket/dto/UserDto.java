package com.ssd.gingermarket.dto;

import org.springframework.web.multipart.MultipartFile;

import com.ssd.gingermarket.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class UserDto {

	@NoArgsConstructor
	@AllArgsConstructor
	@Data 
	public static class Request{
	
		private Long userIdx;
		private String userId;
		private String password;
		private String repeatedPassword;
		private String name;
		private String phone1;
		private String phone2;
		private String phone3;
		private String phone;
		
		private String addr;
		private Long img;
		private String[] items;
		private MultipartFile imageFile;
		
		public User toEntity() {
			return User.builder()
					.userIdx(userIdx)
					.userId(userId)
					.password(password)
					.name(name)
					.address(addr)
					.phone(phone1+phone2+phone3)
					.item1(items[0])
					.item2(items[1])
					.item3(items[2])
				//	.img(img)
					.build();
		}
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Response{
		private Long userIdx;
		private String userId;
		private String password;
		private String name;
		private String phone;
		private String item1;
		private String item2;
		private String item3;
		private String address;
		private Long img;
		
		public Response(User user) {
			this.userIdx=user.getUserIdx();
			this.userId=user.getUserId();
			this.password=user.getPassword();
			this.name=user.getName();
			this.phone=user.getPhone();
			this.item1=user.getItem1();
			this.item2=user.getItem2();
			this.item3=user.getItem3();
			this.img=user.getImg();
			this.address=user.getAddress();
		}
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data 
	public static class LoginRequest{
		private String userId;
		private String password;
	}
	

	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Info {
		private Long userIdx;
		private String userId;
		private String name;
		private String imageUrl;
		private String phone;
		private String item1;
		private String item2;
		private String item3;
		private String address;
	}
}
