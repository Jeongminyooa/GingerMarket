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
		private String phone1;
		private String phone2;
		private String phone3;
		private String[] items;
		private String address;
		private String imageUrl;
		
		public Response(User user) {
			this.userIdx = user.getUserIdx();
			this.userId = user.getUserId();
			this.password = user.getPassword();
			this.name = user.getName();
			setPhoneNumber(user.getPhone());
			items = new String[] {user.getItem1(), user.getItem2(), user.getItem3()};
			if(user.getImage() != null)
				this.imageUrl = user.getImage().getUrl();
			this.address = user.getAddress();
		}
		
		// 전화번호를 3개로 나누어 출력하기 위함
		public void setPhoneNumber(String phone) {
			this.phone1 = phone.substring(0, 3);
			this.phone2 = phone.substring(3, 7);
			this.phone3 = phone.substring(7, 11);
		}
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data 
	public static class LoginRequest{
		private String userId;
		private String password;
	}
}
