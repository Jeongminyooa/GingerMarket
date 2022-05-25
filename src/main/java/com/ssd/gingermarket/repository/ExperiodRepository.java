package com.ssd.gingermarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssd.gingermarket.domain.Experiod;

public interface ExperiodRepository extends JpaRepository<Experiod, Long>{
	public List<Experiod> findAllByUserId(Long userId);
	
	@Query(value = "UPDATE experiod e SET e.d_day = e.d_day - 1", nativeQuery = true)
	public void updateDday();
}
