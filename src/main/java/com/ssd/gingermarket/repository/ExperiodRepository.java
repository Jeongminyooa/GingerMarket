package com.ssd.gingermarket.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssd.gingermarket.domain.Experiod;
import com.ssd.gingermarket.domain.ExperiodType;
import com.ssd.gingermarket.domain.User;

public interface ExperiodRepository extends JpaRepository<Experiod, Long>{
	
	@Query(value = "UPDATE experiod e SET e.d_day = :d_day "
			+ "WHERE e.end_date <= :end_date", nativeQuery = true)
	public void updateDday(@Param("end_date")LocalDate endDate,
			@Param("d_day")ExperiodType dDay);
	
	@Query(value = "SELECT * "
			+ "FROM experiod e "
			+ "WHERE e.author_idx = :author_idx AND e.end_date = :end_date", nativeQuery = true)
	public List<Experiod> findAllByUserIdAndEndDate(@Param("author_idx") Long authorIdx,
													@Param("end_date") LocalDate endDate);

	public List<Experiod> findAllByAuthor(User author);
}
