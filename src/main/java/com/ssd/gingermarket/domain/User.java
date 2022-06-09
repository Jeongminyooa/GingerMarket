package com.ssd.gingermarket.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.stream.events.Comment;

import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity  
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
@DynamicInsert
@Table(name="userinfo")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_idx")
	private Long userIdx;
	
	@Column(name="user_id",unique = true, nullable = false, length=10)
	private String userId;
	
	@Column(name="password",nullable = false, length=16)
	private String password;
	
	@Column(unique = true, nullable = false, length=10)
	private String name;
	
	@Column(length = 11)
	private String phone;
	
	@Column(length = 50)
	private String address;
	
	@Column(length = 10)
	private String item1;
	@Column(length = 10)
	private String item2;
	@Column(length = 10)
	private String item3;
	
	@OneToOne
	@JoinColumn(name="image_idx")
	private Image img;
	
	public boolean matchPassword(String newPassword) {
		return newPassword.equals(password);
	} 

	public void updateUser(String userId, String password, String name,String phone,
			String address,String item1,String item2, String item3) {
		this.userId=userId;
		this.password=password;
		this.name=name;
		this.phone=phone;
		this.address=address;
		this.item1=item1;
		this.item2=item2;
		this.item3=item3;
	}
	
}

	