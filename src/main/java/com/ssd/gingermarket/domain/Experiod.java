package com.ssd.gingermarket.domain;

import lombok.*;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity 
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="experiod") 
public class Experiod {
	 @Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ExperiodSequence")
	 @SequenceGenerator(sequenceName = "ExperiodSequence", name = "ExperiodSequenceGenerator", allocationSize = 1)
	 @Column(name = "experiod_idx",unique = true)
	 private Long id;
	 
	 // 후에 참조 관계 추가
	 @Column(unique = true, nullable = false)
	 private Long userId;
	
	 @Column(length = 22)
	 private String category;
	 
	 @Column(name = "enroll_date")
	 private LocalDateTime enrollDate;
	 
	 @Column(name = "end_date")
	 private LocalDateTime endDate;
	 
	 @Column(name = "d_day")
	 private int dDay;
	 
	 @Builder(builderClassName= "experiod", builderMethodName = "experiodBuilder")
	 public Experiod(Long userId, String category, int dDay) {
		 this.userId = userId;
		 this.category = category;
		 this.dDay = dDay;
		 
		 // 등록일
		 this.enrollDate = LocalDateTime.now();
		 
		 // 마감일 계산
		 this.endDate = enrollDate.plusDays(dDay);
	 }
	 
}
