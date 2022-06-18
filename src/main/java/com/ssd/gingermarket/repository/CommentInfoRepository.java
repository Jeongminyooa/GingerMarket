package com.ssd.gingermarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssd.gingermarket.domain.CommentInfo;

public interface CommentInfoRepository extends JpaRepository<CommentInfo, Long>{

	@Query(value = "SELECT * "
			+ "FROM commentinfo c "
			+ "WHERE c.parent_idx IS NULL AND c.group_idx = :group_idx", nativeQuery = true)
	List<CommentInfo> findByGroupIdx(@Param("group_idx")Long groupIdx);

}
