package com.ssd.gingermarket.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ssd.gingermarket.domain.Apply;
import com.ssd.gingermarket.domain.GroupBuying;
import com.ssd.gingermarket.domain.User;


public interface ApplyRepository extends JpaRepository<Apply, Long>{

	List<Apply> findByGroupBuyingOrderByApplyIdxDesc(GroupBuying groupBuying);
	Apply findByGroupBuyingAndAuthor(GroupBuying groupBuying, User author);

}


