package com.ssd.gingermarket.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssd.gingermarket.domain.Experiod;

public interface ExperiodRepository extends JpaRepository<Experiod, Long>{
	public List<Experiod> findAllByUserId(Long userId);
	
	@Query(value = "UPDATE experiod e SET e.d_day = e.d_day - 1", nativeQuery = true)
	public void updateDday();
	
	@Query(value = "SELECT * "
			+ "FROM experiod e "
			+ "WHERE e.user_id = :user_id AND e.d_day = :d_day", nativeQuery = true)
	public List<Experiod> findAllByUserIdAndEndDate(@Param("user_id") Long userId,
													@Param("d_day") int dDay);
}
