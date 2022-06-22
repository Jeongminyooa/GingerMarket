package com.ssd.gingermarket.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	private Long userIdx;
	
	@Column(unique = true, nullable = false, length=10)
	private String userId;
	
	@Column(nullable = false, length=16)
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
	@JoinColumn(name="imgIdx")
	private Image image;

	
	@OneToMany(mappedBy = "author", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Experiod> experiodList = new ArrayList<Experiod>();
	
	@OneToMany(mappedBy = "author", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<SharePost> shareList = new ArrayList<SharePost>();

	@OneToMany(mappedBy = "author", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<GroupBuying> groupBuyingList = new ArrayList<GroupBuying>();

	@OneToMany(mappedBy="author", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Apply> applyList = new ArrayList<Apply>();

	@OneToMany(mappedBy = "roomIdx")
	private List<MessageRoom> messageRoomList = new ArrayList<MessageRoom>();
	
	@OneToMany(mappedBy = "sender", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<MessageRoom> participatemessageRoomList = new ArrayList<MessageRoom>();
	
	@OneToMany(mappedBy = "author", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<CommentInfo> commentList = new ArrayList<CommentInfo>();
	
	public boolean matchPassword(String newPassword) {
		return newPassword.equals(password);
	} 

	public void updateUser(String name,String phone,
			String address, String[] items) {
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.item1 = items[0];
		if(items.length == 1) {
			this.item2 = null;
			this.item3 = null;
		}
		else if(items.length == 2) {
			this.item2 = items[1];
			this.item3 = null;
		} else {
			this.item2 = items[1];
			this.item3 = items[2];
		}
	}
	
	public void updateProfileImage(Image image) {
		this.image = image;
	}
	
	public void updatePassword(String password) {
		this.password = password;
	}
	
	

}

	