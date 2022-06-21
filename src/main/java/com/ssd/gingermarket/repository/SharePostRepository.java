package com.ssd.gingermarket.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssd.gingermarket.domain.GroupBuying;
import com.ssd.gingermarket.domain.SharePost;
import com.ssd.gingermarket.domain.User;

public interface SharePostRepository extends JpaRepository<SharePost, Long>{
	public List<SharePost> findAllByAuthor(User author);
	public SharePost findTop1ByCategoryOrderByCreatedDateDesc(String category);
	
	@Query(value = "SELECT * FROM SharePost p "
	   		+ "WHERE (p.title LIKE %:keyword%) ", nativeQuery = true)
	Page<SharePost> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
	
	@Query(value = "SELECT * FROM SharePost p "
	   		+ "WHERE (p.address LIKE %:address%) ", nativeQuery = true)
	Page<SharePost> findByAddress(@Param("address") String keyword, Pageable pageable);
	   
	@Query(value = "SELECT * FROM SharePost p "
		   		+ "WHERE (p.category LIKE %:category%) ", nativeQuery = true)
    Page<SharePost> findByCategory(@Param("category") String category, Pageable pageable);
}