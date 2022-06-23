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
	 @Column(name = "experiod_idx", unique = true)
	 private Long experiodIdx;
	 
	 @ManyToOne(fetch = FetchType.LAZY, optional = false)
	 @JoinColumn(name = "author_idx")
	 private User author;
	
	 @Column(length = 22, nullable = false)
	 private String category;
	 
	 private LocalDate endDate;
	 
	 @Column(nullable = false)
	 @Enumerated(value = EnumType.STRING)
	 private ExperiodType status;
	 
	 @Builder(builderClassName= "experiod", builderMethodName = "experiodBuilder")
	 public Experiod(User author, String category, ExperiodType status) {
		 this.author = author;
		 this.category = category;
		 this.status = status;
	 }
	 
	// 마감일 계산
	 public void caculateEndDate(int day) {
		 this.endDate = getCreatedDate().toLocalDate().plusDays(day);
	 }
	 
}
