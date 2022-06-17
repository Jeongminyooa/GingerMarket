package com.ssd.gingermarket.domain;


import javax.persistence.*;

import lombok.*;

@Entity 
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="image") 
public class Image {
	 @Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ImageSequence")
	 @SequenceGenerator(sequenceName = "ImageSequence", name = "ImageSequenceGenerator", allocationSize = 1)
	 @Column(name = "image_idx",unique = true)
	 private Long id;
	 
	 @Column(nullable = false)
	 private String url;
	 
	 public void updateImage(String url) {
		 this.url = url;
	 }
	
}
