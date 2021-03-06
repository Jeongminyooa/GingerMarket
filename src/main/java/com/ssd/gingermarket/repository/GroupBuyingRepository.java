package com.ssd.gingermarket.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ssd.gingermarket.domain.GroupBuying;
import com.ssd.gingermarket.domain.User;

public interface GroupBuyingRepository extends JpaRepository<GroupBuying, Long> {
	 public GroupBuying findTop1ByCategoryOrderByCreatedDateDesc(String category);
	 public List<GroupBuying> findAllByAuthor(User author);

	 
	@Query(value = "SELECT * FROM groupbuyingpost g "
	   		+ "WHERE (g.title LIKE %:keyword%) ", nativeQuery = true)
	Page<GroupBuying> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
	   
	@Query(value = "SELECT * FROM groupbuyingpost g "
		   		+ "WHERE (g.category LIKE %:category%) ", nativeQuery = true)
	Page<GroupBuying> findByCategory(@Param("category") String category, Pageable pageable);

}