package com.ssd.gingermarket.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssd.gingermarket.domain.GroupBuying;
import com.ssd.gingermarket.dto.ApplyDto.Info;
import com.ssd.gingermarket.dto.GroupBuyingDto;
import com.ssd.gingermarket.repository.GroupBuyingRepository;

import lombok.RequiredArgsConstructor;


//@Slf4j
@RequiredArgsConstructor //final이 붙은 필드 생성 
@Service
public class GroupBuyingServiceImpl implements GroupBuyingService {

	private final GroupBuyingRepository groupBuyingRepository;

	// 포스트 등록
	@Override
	@Transactional
	public void addPost(GroupBuyingDto.Request groupBuying) {
		groupBuyingRepository.save(groupBuying.toEntity());
	}

	// 포스트 전체 조회
	@Override
	@Transactional(readOnly = true)
	public List<GroupBuyingDto.CardResponse> getAllPost() {
		List<GroupBuying> groupBuyingList = groupBuyingRepository.findAll(Sort.by(Direction.DESC, "createDate"));
	 
		return groupBuyingList.stream().map(GroupBuyingDto.CardResponse::new).collect(Collectors.toList());	
	}

	// 포스트 상세 조회
	@Override
	@Transactional(readOnly = true)
	public GroupBuyingDto.DetailResponse getPost(Long groupIdx) {
		GroupBuying groupBuying = groupBuyingRepository.findById(groupIdx).orElseThrow();    

		return new GroupBuyingDto.DetailResponse(groupBuying);
	}
	
	// 포스트 상세 정보
	@Override
	@Transactional(readOnly = true)
	public GroupBuyingDto.Request getPostForModify(Long groupIdx) {
		GroupBuying groupBuying = groupBuyingRepository.findById(groupIdx).orElseThrow();
		return new GroupBuyingDto.Request(groupBuying);
	}

	// 포스트 수정
	@Override
    @Transactional
    public void modifyPost(Long groupIdx, GroupBuyingDto.Request dto) {
		GroupBuying groupBuying = groupBuyingRepository.findById(groupIdx).orElseThrow(); 
		groupBuying.updatePost(dto.getTitle(), dto.getCategory(), dto.getRecruitNum(), dto.getWebsite(), dto.getPrice(), dto.getDescr(), dto.getEndDate(),dto.getImageIdx());
    }

    // 포스트 삭제
    @Override
    @Transactional
    public void removePost(Long groupIdx) {
    	groupBuyingRepository.deleteById(groupIdx);
    }

	@Override
	public List<Info> getAllApply() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addApplyForm() {
		// TODO Auto-generated method stub
		
	}

}
	

