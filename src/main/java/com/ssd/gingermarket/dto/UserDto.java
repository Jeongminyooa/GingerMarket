package com.ssd.gingermarket.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.ssd.gingermarket.domain.Image;
import com.ssd.gingermarket.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class UserDto {
	@NoArgsConstructor
	@AllArgsConstructor
	@Data 
	public static class Info{
		private Long userIdx;
		private String userId;
		private String password;
		private String name;
		private String phone;
		private String address;
		private String item1;
		private String item2;
		private String item3;
		private Image imageIdx;
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data 
	public static class Request{
	
		private Long userIdx;
		@Size(min=5, max=10)
		@NotBlank(message="id를 입력해주세요")
		private String userId;
		@NotBlank
		private String password;
		@NotBlank
		private String repeatedPassword;
		@NotBlank
		private String name;
		private String phone1;
		@NotBlank
		private String phone2;
		@NotBlank
		private String phone3;
		private String phone;
		
		private String addr;
		private String[] items;
		
		private Image image;
		private String imgUrl;
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
					.image(image)
					.build();
		}
		
		public Request(User user) {
			this.userId=user.getUserId();
			this.userIdx=user.getUserIdx();
			this.password=user.getPassword();
			this.name=user.getName();
			this.phone=user.getPhone();
			this.items[0]=user.getItem1();
			this.items[1]=user.getItem2();
			this.items[2]=user.getItem3();
			this.addr=user.getAddress();
			try {
				this.imgUrl="/upload/"+user.getImage().getUrl();
			} catch (Exception e ) {	           
				this.imgUrl = null;
			}
			
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
		private Image img;
		
		public Response(User user) {
			this.userIdx=user.getUserIdx();
			this.userId=user.getUserId();
			this.password=user.getPassword();
			this.name=user.getName();
			this.phone=user.getPhone();
			this.item1=user.getItem1();
			this.item2=user.getItem2();
			this.item3=user.getItem3();
			this.img=user.getImage();
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
}
