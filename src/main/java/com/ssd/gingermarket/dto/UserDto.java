package com.ssd.gingermarket.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
		
		@Size(min=5,message="아이디는 5자 이상이어야 합니다")
		@Size(max=10,message="아이디는 10자 이하여야 합니다")
		private String userId;
		
		@Size(min=5,message="비밀번호는 5자 이상이어야 합니다")
		@Size(max=16,message="비밀번호는 16자 이하여야 합니다")
		private String password;
		
		@NotBlank(message="비밀번호를 입력해주세요")
		private String repeatedPassword;
		
		@NotBlank(message="닉네임을 입력해주세요")
		@Size(max=10,message="닉네임은 10자 이하여야 합니다")
		private String name;
		
		private String phone1;
		
		@NotBlank(message="휴대폰번호를 입력해주세요")
		private String phone2;
		
		@NotBlank(message="휴대폰번호를 입력해주세요")
		private String phone3;
		
		private String phone;
		private String[] items;
		private String addr;
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
					.imageIdx(image.getId())
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
		@NotEmpty(message="{notnull.name}")
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
