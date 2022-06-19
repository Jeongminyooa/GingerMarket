package com.ssd.gingermarket.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import javax.persistence.PrePersist;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="messageroom")
public class MessageRoom extends BaseTime {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MessageRoomSequence")
	@SequenceGenerator(sequenceName = "MessageRoomSequence", name = "MessageRoomSequenceGenerator", allocationSize = 1)
	@Column(unique=true)
	private Long roomIdx;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_idx")
	private SharePost post;
	
	@ManyToOne
	@JoinColumn(name = "author_idx") 
	private User author;
	
	@ManyToOne
	@JoinColumn(name = "sender_idx")
	private User sender;
	
	@OneToMany(mappedBy="room", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<MessageInfo> messages = new ArrayList<MessageInfo>();
	
	
	@PrePersist
	public void prePersist(){
		if(this.messages != null) {
			Collections.sort(this.messages, new Comparator<MessageInfo>() {
			    @Override
			    public int compare(MessageInfo m1, MessageInfo m2) {
			        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
			        return m1.getMessageIdx() > m2.getMessageIdx() ? -1 : (m1.getMessageIdx() < m2.getMessageIdx()) ? 1 : 0;
			    }
			});
		}
	}
	
}
