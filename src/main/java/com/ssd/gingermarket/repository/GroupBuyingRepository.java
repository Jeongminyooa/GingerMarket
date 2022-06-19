package com.ssd.gingermarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssd.gingermarket.domain.GroupBuying;

public interface GroupBuyingRepository extends JpaRepository<GroupBuying, Long>{


	   @Query(value = "SELECT * FROM groupbuyingpost g "
	   		+ "WHERE (g.title LIKE %:keyword%) "
	   		+ "ORDER BY g.created_date DESC", nativeQuery = true)
			 List<GroupBuying> findByKeyword(@Param("keyword") String keyword);
	   
	   @Query(value = "SELECT * FROM groupbuyingpost g "
		   		+ "WHERE (g.category LIKE %:category%) "
		   		+ "ORDER BY g.created_date DESC", nativeQuery = true)
				 List<GroupBuying> findByCategory(@Param("category") String category);


}