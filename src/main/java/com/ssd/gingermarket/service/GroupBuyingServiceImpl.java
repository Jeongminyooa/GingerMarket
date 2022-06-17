package com.ssd.gingermarket.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssd.gingermarket.domain.Apply;
import com.ssd.gingermarket.domain.GroupBuying;
import com.ssd.gingermarket.dto.ApplyDto;

import com.ssd.gingermarket.dto.GroupBuyingDto;
import com.ssd.gingermarket.repository.ApplyRepository;
import com.ssd.gingermarket.repository.GroupBuyingRepository;

import lombok.RequiredArgsConstructor;


//@Slf4j
@RequiredArgsConstructor //final이 붙은 필드 생성 
@Service
public class GroupBuyingServiceImpl implements GroupBuyingService {

	private final GroupBuyingRepository groupBuyingRepository;
	private final ApplyRepository applyRepository;

	// 포스트 등록
	@Override
	@Transactional
	public void addPost(GroupBuyingDto.Request groupBuying) {
		GroupBuying groupBuyingEntity = groupBuyingRepository.save(groupBuying.toEntity());

		int progress = updateProgress(groupBuyingEntity.getParticipateNum(), groupBuyingEntity.getRecruitNum());
		groupBuyingEntity.updateProgress(progress);		
	}

	// 포스트 전체 조회
	@Override
	@Transactional(readOnly = true)
	public List<GroupBuyingDto.CardResponse> getAllPost() {
		List<GroupBuying> groupBuyingList = groupBuyingRepository.findAll(Sort.by(Direction.DESC, "createdDate"));
		
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


		int progress = updateProgress(groupBuying.getParticipateNum(), groupBuying.getRecruitNum());
		groupBuying.updateProgress(progress);
    }

    // 포스트 삭제
    @Override
    @Transactional
    public void removePost(Long groupIdx) {
    	groupBuyingRepository.deleteById(groupIdx);
    }

	// 공구 신청 조회
    @Override
	@Transactional(readOnly = true)
	public List<ApplyDto.Response> getAllApply() {
		List<Apply> applyList = applyRepository.findAll(Sort.by(Direction.DESC, "applyIdx"));
	 
		return applyList.stream().map(ApplyDto.Response::new).collect(Collectors.toList());	
	}
    
    // 공구 신청 등록
    @Override
	@Transactional
	public void addApply(ApplyDto.Request apply, Long groupIdx) {
		applyRepository.save(apply.toEntity());
		GroupBuying groupBuying = groupBuyingRepository.findById(groupIdx).orElseThrow();
		groupBuying.updateParticipate();
		
		int progress = updateProgress(groupBuying.getParticipateNum(), groupBuying.getRecruitNum());
		groupBuying.updateProgress(progress);

	}
    
    //공구 진행 상태
   	public int updateProgress(int partipateNum, int recruitNum) {
   		if(partipateNum == recruitNum) {
   			// 마감
   			return 2;
   			
   		} else if ((partipateNum == recruitNum - 1) || (recruitNum ==  1) ) {
   			// 마감임박
   			return 1;
   		} 
       	else
   			return 0;
   	}

}
	

