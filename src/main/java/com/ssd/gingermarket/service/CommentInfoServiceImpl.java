package com.ssd.gingermarket.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssd.gingermarket.domain.CommentInfo;
import com.ssd.gingermarket.domain.GroupBuying;
import com.ssd.gingermarket.domain.User;
import com.ssd.gingermarket.dto.CommentDto;
import com.ssd.gingermarket.dto.CommentDto.Request;
import com.ssd.gingermarket.repository.CommentInfoRepository;
import com.ssd.gingermarket.repository.GroupBuyingRepository;
import com.ssd.gingermarket.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentInfoServiceImpl implements CommentInfoService {
	
	private final CommentInfoRepository commentInfoRepository;
	private final UserRepository userRepository;
	private final GroupBuyingRepository groupBuyingRepository;

	@Override
	@Transactional
	public void addComment(CommentDto.Request request, Long authorIdx, Long groupIdx) {
		// user entity
		User author = userRepository.findById(authorIdx).orElseThrow();
		
		// group entity
		GroupBuying groupBuying = groupBuyingRepository.findById(groupIdx).orElseThrow();
		
		CommentInfo comment = CommentInfo.commentBuilder()
				.content(request.getContent())
				.group(groupBuying)
				.author(author)
				.build();
		
		commentInfoRepository.saveAndFlush(comment);
		
		groupBuying.getCommentList().add(comment);
		return;
	}
	
	@Override
	@Transactional
	public void addChildComment(Request request, Long authorIdx, Long groupIdx, Long parentIdx) {
		// user entity
		User author = userRepository.findById(authorIdx).orElseThrow();
		
		// post entity
		GroupBuying groupBuyingPost = groupBuyingRepository.findById(groupIdx).orElseThrow();
		
		// 부모 댓글의 entity 
		CommentInfo parentComment = commentInfoRepository.findById(parentIdx).orElseThrow(null);
				
		CommentInfo childComment = CommentInfo.commentBuilder()
				.content(request.getContent())
				.parentIdx(parentComment)
				.group(groupBuyingPost)
				.author(author)
				.build();
		
		parentComment.getChildCommentList().add(childComment);
		
		// 댓글 추가
		commentInfoRepository.save(childComment);
		
		// 포스트의 댓글 추가
		groupBuyingPost.getCommentList().add(childComment);
		return;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommentDto.Info> getCommentList(Long groupIdx) {
		// TODO Auto-generated method stub
		
		GroupBuying groupBuyingPost = groupBuyingRepository.findById(groupIdx).orElseThrow();
		User author = groupBuyingPost.getAuthor();
		
		List<CommentInfo> commentInfoList = commentInfoRepository.findByGroupIdx(groupIdx);
		
		List<CommentDto.Info> dto = commentInfoList.stream().map(co -> new CommentDto.Info(
				co.getId(),
				co.getContent(),
				co.getCreatedDate(),
				co.getUpdateDate(),
				(co.getIsDeleted().equals("N") ? false : true),
				co.getAuthor().getUserIdx(),
				(co.getAuthor().getImage() == null ? null : co.getAuthor().getImage().getUrl()),
				co.getAuthor().getName(),
				ofList(co.getChildCommentList(), author.getUserIdx()),
				(author.getUserIdx() == co.getAuthor().getUserIdx() ? true : false)))
				.collect(Collectors.toList());

		return dto;
	}

	// 대댓글 entity -> dto
	public List<CommentDto.ChildInfo> ofList (List<CommentInfo> list, Long postAuthorIdx) {
		if(list.size() == 0) {
			return new ArrayList<CommentDto.ChildInfo>();
		}
		
		return list.stream().map(ch -> new CommentDto.ChildInfo(
				ch.getId(),
				ch.getParentIdx().getId(),
				ch.getContent(),
				ch.getCreatedDate(),
				ch.getUpdateDate(),
				(ch.getIsDeleted().equals("N") ? false : true),
				ch.getAuthor().getUserIdx(),
				(ch.getAuthor().getImage() == null ? null : ch.getAuthor().getImage().getUrl()),
				ch.getAuthor().getName(),
				(postAuthorIdx == ch.getAuthor().getUserIdx() ? true : false)
				))
				.collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public void updateComment(CommentDto.Request request, Long commentIdx) {
		// TODO Auto-generated method stub
		CommentInfo comment = commentInfoRepository.findById(commentIdx).orElseThrow(null);
		comment.updateComment(request.getContent());
	}

	@Override
	@Transactional
	public void removeComment(Long commentIdx) {
		// TODO Auto-generated method stub
		CommentInfo comment = commentInfoRepository.findById(commentIdx).orElseThrow(null); 
		Long groupIdx = comment.getGroup().getGroupIdx();
		
		comment.removeComment();
		}

}
