package com.ssd.gingermarket.domain;

import lombok.*;

import java.time.LocalDate;

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
	 
	 // 후에 참조 관계 추가, author 컬럼명으로 변경
	 @Column(nullable = false)
	 private Long userId;
	
	 @Column(length = 22)
	 private String category;
	 
	 private LocalDate enrollDate;
	 
	 private LocalDate endDate;
	 
	 private int dDay;
	 
	 @Builder(builderClassName= "experiod", builderMethodName = "experiodBuilder")
	 public Experiod(Long userId, String category, int dDay) {
		 this.userId = userId;
		 this.category = category;
		 this.dDay = dDay;
		 
		 // 등록일
		 this.enrollDate = LocalDate.now();
		 
		 // 마감일 계산
		 this.endDate = enrollDate.plusDays(dDay);
	
	 }
	 
	 // 디데이 업데이트
	 public int updateDday() {
		 this.dDay--;
		 return dDay;
	 }
	 
}
