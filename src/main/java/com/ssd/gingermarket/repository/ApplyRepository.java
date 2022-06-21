package com.ssd.gingermarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssd.gingermarket.domain.Apply;


public interface ApplyRepository extends JpaRepository<Apply, Long>{

	@Query(value = "SELECT * "
			+ "FROM applyinfo a "
			+ "WHERE a.group_idx = :group_idx"
			+ "ORDER BY a.apply_idx DESC", nativeQuery = true)
	List<Apply> findByGroupIdx(@Param("group_idx")Long groupIdx);

}


