package com.ssd.gingermarket.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssd.gingermarket.domain.Apply;
import com.ssd.gingermarket.domain.GroupBuying;
import com.ssd.gingermarket.domain.User;
import com.ssd.gingermarket.dto.ApplyDto;

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
    	
    	// user entity
    	// User author = userRepository.findById(authorIdx).orElseThrow();
    	
		List<Apply> applyList = applyRepository.findByGroupIdx(groupIdx);
	 
		return applyList.stream().map(ApplyDto.Info::new).collect(Collectors.toList());	
	}
    
    // 공구 신청 등록
    @Override
	@Transactional
	public void addApply(ApplyDto.Info apply, Long groupIdx) {
    	
		User author = userRepository.findById(apply.getAuthorIdx()).orElseThrow();
		apply.setAuthor(author);
	
    	GroupBuying groupBuying = groupBuyingRepository.findById(groupIdx).orElseThrow();
    
		groupBuying.updateParticipate();	
		int progress = updateProgress(groupBuying.getParticipateNum(), groupBuying.getRecruitNum());
		groupBuying.updateProgress(progress); 
		
		
		applyRepository.save(apply.toEntity());
		
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
	
