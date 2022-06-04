package com.ssd.gingermarket.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ssd.gingermarket.domain.CommentInfo;
import com.ssd.gingermarket.dto.CommentDto;
import com.ssd.gingermarket.dto.CommentDto.Info;
import com.ssd.gingermarket.dto.CommentDto.Request;
import com.ssd.gingermarket.repository.CommentInfoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentInfoServiceImpl implements CommentInfoService {
	
	private final CommentInfoRepository commentInfoRepository;

	@Override
	public void addComment(Request request) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Info> getCommentList(Long groupIdx) {
		// TODO Auto-generated method stub
		
		// post 정보 가져오기
		// GroupBuyingPost groupPostEntity = GroupBuyingRepository.findById(groupIdx);
		List<CommentInfo> commentInfoList = commentInfoRepository.findByGroupIdx(groupIdx);
		
		// int commentCount = groupPostEntity.getCommentList().size();
		List<CommentDto.Info> dto = commentInfoList.stream().map(co -> new CommentDto.Info(co.getId()
				, co.getContent(), co.getUpdateDate() ,co.getUpdateDate()
				, (co.getIsDeleted().equals("0") ? false : true)
				, co.getParentIdx().getId()
				, co.getGroupIdx()
				, co.getAuthorIdx()))
				.collect(Collectors.toList());

		return null;
	}

	@Override
	public void updateComment(Request request) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeComment(Long commentIdx) {
		// TODO Auto-generated method stub

	}

}
