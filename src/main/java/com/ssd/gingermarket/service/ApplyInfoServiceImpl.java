package com.ssd.gingermarket.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssd.gingermarket.domain.Apply;
import com.ssd.gingermarket.domain.GroupBuying;
import com.ssd.gingermarket.domain.User;
import com.ssd.gingermarket.dto.ApplyDto;
import com.ssd.gingermarket.dto.UserDto;

import com.ssd.gingermarket.dto.GroupBuyingDto;
import com.ssd.gingermarket.repository.ApplyRepository;
import com.ssd.gingermarket.repository.GroupBuyingRepository;
import com.ssd.gingermarket.repository.UserRepository;

import lombok.RequiredArgsConstructor;


//@Slf4j
@RequiredArgsConstructor //final이 붙은 필드 생성 
@Service
public class ApplyInfoServiceImpl implements ApplyInfoService {

	private final ApplyRepository applyRepository;
	private final GroupBuyingRepository groupBuyingRepository;
	private final UserRepository userRepository;
	
	// 공구 신청 조회
    @Override
	@Transactional(readOnly = true)
	public List<ApplyDto.Info> getAllApply(Long groupIdx) {
    	
    	GroupBuying groupBuying = groupBuyingRepository.findById(groupIdx).orElseThrow();
		List<Apply> applyList = applyRepository.findByGroupBuyingOrderByApplyIdxDesc(groupBuying);
	 
		return applyList.stream().map(ApplyDto.Info::new).collect(Collectors.toList());	
	}
    
    // 공구 신청 등록
    @Override
	@Transactional
	public void addApply(ApplyDto.Info apply, Long userIdx, Long groupIdx) {
    	
		User author = userRepository.findById(userIdx).orElseThrow();
    	GroupBuying groupBuying = groupBuyingRepository.findById(groupIdx).orElseThrow();
    
		groupBuying.updateParticipate();	
		int progress = updateProgress(groupBuying.getParticipateNum(), groupBuying.getRecruitNum());
		groupBuying.updateProgress(progress); 
		
		
		applyRepository.save(apply.toEntity(groupBuying, author));
		
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

	@Override
	@Transactional(readOnly = true)
	public List<GroupBuyingDto.MyPageInfo> getAllApplyByAuthor(Long userIdx) {
		// TODO Auto-generated method stub
		
		User author = userRepository.findById(userIdx).orElseThrow();
		List<Apply> applyList = applyRepository.findByAuthor(author);
		
		List<GroupBuyingDto.MyPageInfo> list = applyList.stream().map(a -> new GroupBuyingDto.MyPageInfo(
				a.getGroupBuying().getGroupIdx(),
				(a.getGroupBuying().getImage() == null ? "" : a.getGroupBuying().getImage().getUrl()),
				a.getGroupBuying().getTitle(),
				a.getGroupBuying().getProgress(),
				a.getGroupBuying().getPrice(),
				a.getGroupBuying().getEndDate()
				)).collect(Collectors.toList());	
		
		return list;
	}
}
	

