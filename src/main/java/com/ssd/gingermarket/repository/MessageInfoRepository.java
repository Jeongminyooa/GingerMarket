package com.ssd.gingermarket.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssd.gingermarket.domain.MessageInfo;
import com.ssd.gingermarket.domain.MessageRoom;
import com.ssd.gingermarket.domain.SharePost;
import com.ssd.gingermarket.dto.SharePostDto;
import com.ssd.gingermarket.dto.SharePostDto.DetailResponse;

public interface MessageInfoRepository extends JpaRepository<MessageInfo, Long>{
	//쪽지 내용 불러오기
	@Query(value = "SELECT m "
			+ "FROM MessageInfo m "
			+ "WHERE m.room.roomIdx = ?1 "
			+ "ORDER BY m.createdDate ASC")
	List<MessageInfo> findAllByRoomIdx(Long roomIdx);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE MessageInfo m "
			+ "SET m.is_read='Y' "
			+ "WHERE m.sender_idx != :sender_idx AND m.room_idx = :room_idx", nativeQuery = true)
	public void updateIsRead(@Param("sender_idx")Long senderIdx
			, @Param("room_idx")Long roomIdx);
	
	
	public MessageInfo findTop1ByRoomOrderByCreatedDateDesc(MessageRoom room);
}
