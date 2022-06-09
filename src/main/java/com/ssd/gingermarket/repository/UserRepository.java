package com.ssd.gingermarket.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssd.gingermarket.domain.Experiod;
import com.ssd.gingermarket.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public User findByUserId(String userId);

	public User findByName(String name);
	
	public User findByUserIdAndPassword(String userId,String password);
	
	public User findByUserIdx(Long userIdx);
	
	

}
