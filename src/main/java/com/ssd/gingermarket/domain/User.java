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
@Getter 
@DynamicInsert
@Table(name="userinfo")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userIdx;
	
	@Column(unique = true, nullable = false)
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
	

//	private Long imageIdx;
	
	@OneToOne
	@JoinColumn(name="imgIdx")
	private Image image;
	
	@OneToMany(mappedBy = "experiodIdx", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Experiod> experiodList = new ArrayList<Experiod>();
	
	/*
	//작성한 나눔 게시글
	private List<SharePost> shareList = new ArrayList<SharePost>();
	//채팅 중인 나눔
	private List<SharePost> participateShareList = new ArrayList<SharePost>();
	//작성한 공구 게시글
	private List<GroupBuying> groupBuyingList = new ArrayList<GroupBuying>();
	//참여한 공구
	private List<GroupBuying> participateGroupBuyingList = new ArrayList<GroupBuying>();
	//작성한 댓글, 대댓글
	private List<CommentInfo> commentList = new ArrayList<CommentInfo>();
	//참여중인 채팅룸
	private List<MessageRoom> messageRoomList = new ArrayList<MessageRoom>();
*/
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

	