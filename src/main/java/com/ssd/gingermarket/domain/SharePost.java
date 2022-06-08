package com.ssd.gingermarket.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PrimaryKeyJoinColumn;
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
//null인 필드값이 insert 시 제외되게하며, default값을 넣어주기 위함 
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
	private String progress;
	
	private int messageCnt = 0;
	
	@OneToOne
	@JoinColumn(name="image_idx")
	private Image image;
	
	@Column(nullable = false)
	private Long authorIdx;
	
	@OneToMany(mappedBy="post")
	private List<Message> messages = new ArrayList<Message>();
	
	@PrePersist
	public void prePersist(){
		this.progress = this.progress == null ? "N" : this.progress;
	}
	
	public void updatePost(String title, String category, String descr, String address, Image image) {
        this.title = title;
        this.category = category;
        this.descr = descr;
        this.address = address;
        this.image = image;
    }
	
	public void updateProgress(boolean prog) {
		if(prog)
			this.progress = "'N'";
		else
			this.progress = "'Y'";
	}
	
	
	
}
