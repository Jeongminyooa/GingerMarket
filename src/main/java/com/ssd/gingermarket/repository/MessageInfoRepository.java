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
import com.ssd.gingermarket.dto.SharePostDto;
import com.ssd.gingermarket.dto.SharePostDto.DetailResponse;

public interface MessageInfoRepository extends JpaRepository<MessageInfo, Long>{
	//쪽지 내용 불러오기
	@Query(value = "SELECT m "
			+ "FROM MessageInfo m "
			+ "WHERE m.room.roomIdx = ?1 "
			+ "ORDER BY m.createdDate ASC")
	List<MessageInfo> findAllByRoomIdx(Long roomIdx);

}
