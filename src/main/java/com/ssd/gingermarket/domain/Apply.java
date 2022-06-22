package com.ssd.gingermarket.domain;

import lombok.*;

import javax.persistence.*;

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
	 
	 @OneToOne
	 @JoinColumn(name="image_idx")
	 private Image image;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "author_idx") 
	 private User author;
	 
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "group_idx")  
	 private GroupBuying groupBuying; 
	

}
