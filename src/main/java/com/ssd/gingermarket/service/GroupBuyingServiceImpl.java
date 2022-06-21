package com.ssd.gingermarket.service;


import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.ssd.gingermarket.domain.User;
import com.ssd.gingermarket.domain.GroupBuying;

import com.ssd.gingermarket.domain.User;
import com.ssd.gingermarket.dto.ApplyDto;

import com.ssd.gingermarket.dto.GroupBuyingDto;
import com.ssd.gingermarket.dto.GroupBuyingDto.MyPageInfo;
import com.ssd.gingermarket.repository.ApplyRepository;

import com.ssd.gingermarket.repository.GroupBuyingRepository;
import com.ssd.gingermarket.repository.UserRepository;

import lombok.RequiredArgsConstructor;


//@Slf4j
@RequiredArgsConstructor //final이 붙은 필드 생성 
@Service
public class GroupBuyingServiceImpl implements GroupBuyingService {

	private final GroupBuyingRepository groupBuyingRepository;
	private final ApplyRepository applyRepository;
	private final UserRepository userRepository;


	// 포스트 등록
	@Override
	@Transactional
	public void addPost(GroupBuyingDto.Request groupBuying) {
		User author = userRepository.findById(groupBuying.getAuthorIdx()).orElseThrow();
		groupBuying.setAuthor(author);
		GroupBuying groupBuyingEntity = groupBuyingRepository.save(groupBuying.toEntity());

		int progress = updateProgress(groupBuyingEntity.getParticipateNum(), groupBuyingEntity.getRecruitNum());
		groupBuyingEntity.updateProgress(progress);		
	}

	// 포스트 전체 조회
	@Override
	@Transactional(readOnly = true)
	public Page<GroupBuying> getAllPost(int page) {
		Pageable pageable = PageRequest.of(page, 8, Sort.by(Direction.DESC, "createdDate") );
		
		return this.groupBuyingRepository.findAll(pageable);
	}
	
	//선호 카테고리 포스트 조회 
		@Override
		@Transactional(readOnly = true)
	    public List<GroupBuyingDto.DetailResponse> getFavPost(Long userIdx) {
			User user = userRepository.findById(userIdx).orElseThrow();

			List<String> categories = new ArrayList<>();
			String item = user.getItem1();
			if(item != null)
				categories.add(user.getItem1());
			item = user.getItem2();
			if(item != null)
				categories.add(user.getItem2());
			item = user.getItem3();
			if(item != null)
				categories.add(user.getItem3());

			List<GroupBuying> postList = new ArrayList<>();

			for(int i = 0; i < categories.size(); i++) {
				Optional<GroupBuying> post = Optional.ofNullable(groupBuyingRepository.findTop1ByCategoryOrderByCreatedDateDesc(categories.get(i)));
				if(post.isPresent())
					postList.add(post.get());
			}

	        return postList.stream().map(GroupBuyingDto.DetailResponse::new).collect(Collectors.toList());
	    }

	// 포스트 상세 조회
	@Override
	@Transactional(readOnly = true)
	public GroupBuyingDto.DetailResponse getPost(Long groupIdx) {
		GroupBuying groupBuying = groupBuyingRepository.findById(groupIdx).orElseThrow();  

		return new GroupBuyingDto.DetailResponse(groupBuying);
	}
	
	// 포스트 상세 정보(update용)
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
		
		if(!dto.getFile().getOriginalFilename().equals(""))
			groupBuying.updatePostImg(dto.getImage());
		
		groupBuying.updatePost(dto.getTitle(), dto.getCategory(), dto.getRecruitNum(), dto.getWebsite(), dto.getPrice(), dto.getDescr(), dto.getEndDate());

		int progress = updateProgress(groupBuying.getParticipateNum(), groupBuying.getRecruitNum());
		groupBuying.updateProgress(progress);
    }

    // 포스트 삭제
    @Override
    @Transactional
    public void removePost(Long groupIdx) {
    	groupBuyingRepository.deleteById(groupIdx);
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

  //사용자가 작성한 포스트리스트 조회
	@Override
	@Transactional
	public List<MyPageInfo> getGroupBuyingByUserIdx(Long userIdx) {
		// TODO Auto-generated method stub
		User author = userRepository.findById(userIdx).orElseThrow();
			
		List<GroupBuying> postEntityList = groupBuyingRepository.findAllByAuthor(author);
			
		List<MyPageInfo> postList = postEntityList.stream().map(post -> new MyPageInfo(
				post.getGroupIdx(),
				(post.getImage() == null ? "" : post.getImage().getUrl()),
				post.getTitle(),
				post.getProgress(),
				post.getPrice(),
				post.getEndDate())
				).collect(Collectors.toList());
			
		return postList;
		
	}

   	
   	//공구 포스트 검색 (제목, 카테고리)
    @Override
 	@Transactional
	public Page<GroupBuying> getAllPostByKeyword(String keyword, int page, String option) {
    	Pageable pageable = PageRequest.of(page, 8, Sort.by(Direction.DESC, "created_date") );
    	
    	if(option.equals("title")) {
    		return this.groupBuyingRepository.findByKeyword(keyword, pageable);
    	} else {
    		return this.groupBuyingRepository.findByCategory(keyword,pageable);
    	}
	
    }
   
}
	

