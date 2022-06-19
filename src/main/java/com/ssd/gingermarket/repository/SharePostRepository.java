package com.ssd.gingermarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssd.gingermarket.domain.SharePost;
import com.ssd.gingermarket.dto.SharePostDto;
import com.ssd.gingermarket.dto.SharePostDto.DetailResponse;

public interface SharePostRepository extends JpaRepository<SharePost, Long>{

//	@Query(value = "SELECT s.postIdx "
//			+ "FROM SharePost s "
//			+ "WHERE s.authorIdx = ?1")
//	List<Long> findByAuthorIdx(Long userIdx);
//	@Query(value = "SELECT s.postIdx "
//			+ "FROM Sharepost s "
//			+ "WHERE s.authorIdx > ?1")
//	List<Long> findByUserIdx(Long userIdx);
}
