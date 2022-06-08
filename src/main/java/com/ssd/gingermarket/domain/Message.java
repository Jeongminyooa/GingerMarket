package com.ssd.gingermarket.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table
public class Message extends BaseTime {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MessageSequence")
	@SequenceGenerator(sequenceName = "MessageSequence", name = "MessageSequenceGenerator", allocationSize = 1)
	@Column(unique=true)
	private Long messageIdx;
	
	@Column(nullable = false)
	private String content;
	
	@Column(length = 3, nullable = false)
	@ColumnDefault("'N'")
	private String isRead;
	
	@ManyToOne
	@JoinColumn(name = "post_idx")
	private SharePost post;
	
	@Column(nullable = false)
	private Long senderIdx;

	@Column(length = 3, nullable = false)
	@ColumnDefault("'N'")
	private String isSender;
	
	
	public void updateReadOrNot(boolean isRead) {
		if(isRead)
			this.isRead = "'Y'";
		else
			this.isRead = "'N'";
	}
	
}
