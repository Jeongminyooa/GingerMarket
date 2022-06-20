package com.ssd.gingermarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssd.gingermarket.domain.GroupBuying;
import com.ssd.gingermarket.domain.User;

public interface GroupBuyingRepository extends JpaRepository<GroupBuying, Long>{

	//public List<GroupBuying> getGroupBuyingByCategory(String category);
	//public List<GroupBuying> getGroupBuyingByTitle(String title);
	
	
	public List<GroupBuying> findAllByAuthorIdx(User author);

}