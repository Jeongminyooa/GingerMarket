package com.ssd.gingermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssd.gingermarket.domain.GroupBuying;

public interface GroupBuyingRepository extends JpaRepository<GroupBuying, Long>{

	//public List<GroupBuying> getGroupBuyingByCategory(String category);
	//public List<GroupBuying> getGroupBuyingByTitle(String title);

}