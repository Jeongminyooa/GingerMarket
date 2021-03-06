package com.ssd.gingermarket.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;
@Entity 
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name="groupbuyingpost")

public class GroupBuying extends BaseTime{

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

		 @DateTimeFormat(pattern = "yyyy-MM-dd")
		 private LocalDate endDate;
		 
		 @Column(length = 1000)
		 private String descr;
		 
		 @Column(nullable = false)
		 private int progress;
		 
		 @Column(nullable = false)
		 private int price;
		 
		 @OneToOne
		 @JoinColumn(name="image_idx")
		 private Image image;
		 
		 @ManyToOne(fetch = FetchType.LAZY)
		 @JoinColumn(name = "author_idx")
		 private User author;
		 

		 @OneToMany(mappedBy = "groupBuying", cascade = CascadeType.ALL)
		 private List<Apply> applyList = new ArrayList<Apply>();
	 
		 @OneToMany(mappedBy="group", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
		 private List<CommentInfo> commentList = new ArrayList<>();
			
		 
		 public void updatePost(String title, String category, int recruitNum, String website, int price, String descr, LocalDate endDate) {
		        this.title = title;
		        this.category = category;
		        this.recruitNum = recruitNum;	     
		        this.website = website;
		        this.price = price;
		        this.descr = descr;
		        this.endDate = endDate;
		    }
		 
		 public void updateParticipate()
		 {
			 this.participateNum = participateNum + 1;
		 }
		 
		 public void updateProgress(int status) 
		 {
			 this.progress = status;
		 }
		 
		 public void updatePostImg(Image image) {
				this.image = image;
			}
		 

}
