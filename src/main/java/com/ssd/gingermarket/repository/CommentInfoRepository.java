package com.ssd.gingermarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssd.gingermarket.domain.CommentInfo;

public interface CommentInfoRepository extends JpaRepository<CommentInfo, Long>{

	List<CommentInfo> findByGroupIdx(Long groupIdx);

}
