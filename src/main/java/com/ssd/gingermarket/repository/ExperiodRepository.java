package com.ssd.gingermarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssd.gingermarket.domain.Experiod;

public interface ExperiodRepository extends JpaRepository<Experiod, Long>{
	public List<Experiod> findAllByUserId(Long userId);
}
