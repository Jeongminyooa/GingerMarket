package com.ssd.gingermarket.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

@Entity 
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name="groupbuyingpost")

public class GroupBuying {

		 @Id
		 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GroupBuyingSequence")
		 @SequenceGenerator(sequenceName = "GroupBuyingSequence", name = "GroupBuyingSequenceGenerator", allocationSize = 1)
		 @Column(unique = true)
		 private Long groupIdx;

		 @Column(length = 20, nullable = false)
		 private String category;
		 
		 @Column(nullable = false)
		 private int recruitNum;
		 
		 @Column(nullable = false)
		 @ColumnDefault("0")
		 private int participateNum;
		 
		 @Column(length = 255, nullable = false)
		 private String title;
		 
		 @Column(length = 255)
		 private String website;

		 private LocalDateTime createDate;

		 @DateTimeFormat(pattern = "yyyy-MM-dd")
		 private LocalDate endDate;
		 
		 @Column(length = 255)
		 private String descr;
		 
		 @Column(nullable = false)
		 private int progress;
		 
		 @Column(nullable = false)
		 private int price;
		 
		 @ColumnDefault("0")
		 private Long imageIdx;
		 
		 @Column(nullable = false)
		 private Long authorIdx;
		 
		 @PrePersist
		 public void createDate() {
			 this.createDate = LocalDateTime.now();
		 }
		 
		 @OneToMany(mappedBy = "groupBuying", fetch = FetchType.EAGER )
		 private List<Apply> applyList;

		 
		 public void updatePost(String title, String category, int recruitNum, String website, int price, String descr, LocalDate endDate, Long imageIdx) {
		        this.title = title;
		        this.category = category;
		        this.recruitNum = recruitNum;	     
		        this.website = website;
		        this.price = price;
		        this.descr = descr;
		        this.endDate = endDate;
		        this.imageIdx = imageIdx;
		    }

}
