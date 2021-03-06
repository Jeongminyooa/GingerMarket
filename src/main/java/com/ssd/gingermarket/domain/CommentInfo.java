package com.ssd.gingermarket.domain;

import java.time.LocalDateTime;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity 
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="commentinfo") 
public class CommentInfo extends BaseTime {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ExperiodSequence")
	@SequenceGenerator(sequenceName = "ExperiodSequence", name = "ExperiodSequenceGenerator", allocationSize = 1)
	@Column(name = "comment_idx", unique = true)
	private Long commentIdx;
	
	private String content;
	
	// 수정 날짜
	private LocalDateTime updateDate;
	
	// N 혹은 Y
	@Column(length = 1)
	private String isDeleted;
	
	// 부모 댓글 인덱스
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_idx")
	private CommentInfo parentIdx;
	
	// 자식 댓글 리스트
	@OneToMany(mappedBy = "parentIdx", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<CommentInfo> childCommentList = new ArrayList<>();
	
	// 게시글 인덱스
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="group_idx")
	private GroupBuying group;
	
	//사용자 인덱스 
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="author_idx")
	private User author;
	
	@Builder(builderClassName= "comment", builderMethodName = "commentBuilder")
	 public CommentInfo(String content, CommentInfo parentIdx, GroupBuying group, User author) {
		 this.content = content;
		 this.parentIdx = parentIdx;
		 this.group = group;
		 this.author = author;
		 this.isDeleted = "N";
	 }
	
	// 수정
	public void updateComment(String content) {
		this.content = content;
		this.updateDate = LocalDateTime.now();
	}
	
	// 삭제
	public void removeComment() {
		this.isDeleted = "Y";
	}
}
