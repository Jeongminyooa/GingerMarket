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

	//방이 존재하는지 확인 
	@Query(value = "SELECT r.roomIdx "
			+ "FROM MessageRoom r "
			+ "WHERE r.post.postIdx = ?1 "
			+ "AND r.author.userIdx = ?2 AND r.sender.userIdx = ?3")
	public Long existsByIds(Long postIdx, Long authorIdx, Long senderIdx);

	//roomIdx 찾기 
	@Query(value = "SELECT r.roomIdx "
			+ "FROM MessageRoom r "
			+ "WHERE r.post.postIdx = ?1 AND r.sender.userIdx = ?2")
	public Long findByPostIdAndSenderId(Long postIdx, Long senderIdx);
	
	//쪽지함들 불러오기
	@Query(value = "SELECT r "
			+ "FROM MessageRoom r "
			+ "WHERE r.author.userIdx = ?1 OR r.sender.userIdx = ?2 "
			+ "ORDER BY r.createdDate DESC")
	List<MessageRoom> findByAuthorIdx(Long authorIdx, Long senderIdx);

	
	public MessageRoom findByPostAndSender(SharePost post, User sender);
	
	
}
