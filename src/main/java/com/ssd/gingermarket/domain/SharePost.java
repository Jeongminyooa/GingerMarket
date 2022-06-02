package com.ssd.gingermarket.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@DynamicInsert //null인 필드값이 insert 시 제외되게하며, default값을 넣어주기 위함 
@Table(name="sharepost")
public class SharePost extends BaseTime {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SharepostSequence")
	@SequenceGenerator(sequenceName = "SharepostSequence", name = "ShareSequenceGenerator", allocationSize = 1)
	@Column(unique=true)
	private Long postIdx;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String category;
	
	@Column(length = 1000)
	private String descr;
	
	@Column(length = 50)
	private String address;
	
	@Column(length = 3)
	@ColumnDefault("'N'")
	private String progress;
	
	@ColumnDefault("0")
	private int messageCnt;
	
	@ColumnDefault("0")
	private Long imageIdx;
	
	@Column(nullable = false)
	private Long authorIdx;
	
	public void updatePost(String title, String category, String descr, String address, Long imageIdx) {
        this.title = title;
        this.category = category;
        this.descr = descr;
        this.address = address;
        this.imageIdx = imageIdx;
    }
	
	public void updateProgress(boolean prog) {
		if(prog)
			this.progress = "'N'";
		else
			this.progress = "'Y'";
	}
	
}