package com.ssd.gingermarket.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="messageinfo")
public class MessageInfo extends BaseTime {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MessageInfoSequence")
	@SequenceGenerator(sequenceName = "MessageInfoSequence", name = "MessageInfoSequenceGenerator", allocationSize = 1)
	@Column(unique=true)
	private Long messageIdx;
	
	@ManyToOne
	@JoinColumn(name = "room_idx")
	private MessageRoom room;
	
	@Column(nullable = false)
	private String content;
	
	@Column(length = 1)
	private String isRead;
	
	@ManyToOne
	@JoinColumn(name = "sender_idx")
	private User sender;
	
	
	@PrePersist
	public void prePersist(){
		this.isRead = this.isRead == null ? "N" : this.isRead;
	}
	
}
