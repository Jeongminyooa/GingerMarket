package com.ssd.gingermarket.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssd.gingermarket.domain.MessageInfo;
import com.ssd.gingermarket.domain.MessageRoom;
import com.ssd.gingermarket.domain.SharePost;
import com.ssd.gingermarket.domain.User;
import com.ssd.gingermarket.dto.SharePostDto;
import com.ssd.gingermarket.dto.SharePostDto.DetailResponse;

public interface MessageRoomRepository extends JpaRepository<MessageRoom, Long>{

	//Room ID 찾기 
	public Long findRoomIdxByPostAndSender(SharePost post, User sender);

	//쪽지함들 불러오기 
	public List<MessageRoom> findByAuthorOrSenderOrderByCreatedDateDesc(User author, User sender);
	
	// 참여한 쪽지함
	List<MessageRoom> findAllMessageBySender(User sender);
}
