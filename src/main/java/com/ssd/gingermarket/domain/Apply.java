package com.ssd.gingermarket.domain;

import lombok.*;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity 
@NoArgsConstructor
@AllArgsConstructor
@Getter
@DynamicInsert
@Builder
@Table(name="applyinfo")

public class Apply {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ApplySequence")
	 @SequenceGenerator(sequenceName = "ApplySequence", name = "ApplySequenceGenerator", allocationSize = 1)
	 @Column(unique = true)
	 private Long applyIdx;

	 
	 @Column(length = 11, nullable = false)
	 private String phone;
	 
	 @Column(length = 255)
	 private String message;
	 
	 @ColumnDefault("0")
	 private Long imageIdx;
	 
	 //추후 참조
	 @Column(nullable = false)
	 private Long userIdx;
	 
	 /*
	 @ManyToOne    
	 @JoinColumn(name = "user_idx")    
	 private User user; */
	 
	 @ManyToOne    
	 @JoinColumn(name = "group_idx")  
	 private GroupBuying groupBuying;
	

}
