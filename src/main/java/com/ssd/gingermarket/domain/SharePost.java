package com.ssd.gingermarket.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@Column(length = 1)
	private String progress;
	
	@OneToOne
	@JoinColumn(name="image_idx")
	private Image image;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_idx")
	private User author;
	
	@OneToMany(mappedBy="post", cascade = CascadeType.ALL)
	private List<MessageRoom> rooms = new ArrayList<MessageRoom>();
	
	@PrePersist
	public void prePersist(){
		this.progress = this.progress == null ? "N" : this.progress;
	}

	
	public void updatePost(String title, String category, String descr, String address) {
        this.title = title;
        this.category = category;
        this.descr = descr;
        this.address = address;
    }
	
	public void updatePostImg(Image image) {
		this.image = image;
	}
	
	public void updateProgress(boolean prog) {
		if(prog)
			this.progress = "N";
		else
			this.progress = "Y";
	}
	
	
	
}
