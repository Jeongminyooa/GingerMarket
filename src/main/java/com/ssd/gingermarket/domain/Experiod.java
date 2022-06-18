package com.ssd.gingermarket.domain;

import lombok.*;

import java.time.LocalDate;

import javax.persistence.*;

@Entity 
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="experiod") 
public class Experiod extends BaseTime{
	 @Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ExperiodSequence")
	 @SequenceGenerator(sequenceName = "ExperiodSequence", name = "ExperiodSequenceGenerator", allocationSize = 1)
	 @Column(name = "experiod_idx",unique = true)
	 private Long experiodIdx;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "author_idx")
	 private User author;
	
	 @Column(length = 22)
	 private String category;
	 
	 private LocalDate endDate;
	 
	 @Enumerated(value = EnumType.STRING)
	 private ExperiodType dDay;
	 
	 @Builder(builderClassName= "experiod", builderMethodName = "experiodBuilder")
	 public Experiod(User author, String category, ExperiodType dDay) {
		 this.author = author;
		 this.category = category;
		 this.dDay = dDay;
	 }
	 
	// 마감일 계산
	 public void caculateEndDate(int day) {
		 this.endDate = getCreatedDate().toLocalDate().plusDays(day);
	 }
	 
}
