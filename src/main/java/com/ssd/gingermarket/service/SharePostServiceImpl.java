package com.ssd.gingermarket.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssd.gingermarket.domain.SharePost;
import com.ssd.gingermarket.domain.User;
import com.ssd.gingermarket.dto.SharePostDto;
import com.ssd.gingermarket.dto.SharePostDto.DetailResponse;
import com.ssd.gingermarket.dto.TestDto;
import com.ssd.gingermarket.repository.SharePostRepository;
import com.ssd.gingermarket.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Slf4j
@RequiredArgsConstructor // final이 붙은 필드 생성
@Service
public class SharePostServiceImpl implements SharePostService {
	private final SharePostRepository sharePostRepository;
	private final UserRepository userRepository;

	//게시글 등록
	@Override
	@Transactional
	public void addPost(SharePostDto.Request dto) {
		sharePostRepository.save(dto.toEntity()).getPostIdx();
	}

	
	//게시글 상세정보 조회
	@Override
	@Transactional(readOnly = true)
    public SharePostDto.DetailResponse getPost(Long postIdx) {
        SharePost entity = sharePostRepository.findById(postIdx).orElseThrow();       
        return new SharePostDto.DetailResponse(entity);
    }
	
	//게시글 상세정보(update용)
	@Override
	@Transactional(readOnly = true)
    public SharePostDto.Request getPostForModify(Long postIdx) {
		SharePost entity = sharePostRepository.findById(postIdx).orElseThrow();
        return new SharePostDto.Request(entity);
    }
	
	//사용자가 작성한 포스트리스트 조회
	public List<SharePostDto.MyPageInfo> getPostByUserId(Long userIdx) {
		User author = userRepository.findById(userIdx).orElseThrow();
		
		List<SharePost> postEntityList = sharePostRepository.findAllByAuthorIdx(author);
		
		List<SharePostDto.MyPageInfo> postList = postEntityList.stream().map(post -> new SharePostDto.MyPageInfo(
				post.getPostIdx(),
				//post.getImage().getUrl(),
				"",
				post.getTitle(),
				(post.getProgress().equals("Y") ? "나눔 완료" : "진행중"),
				post.getCreatedDate())
				).collect(Collectors.toList());
		
		return postList;
	}
		
	//게시글 리스트 조회
	@Override
	@Transactional(readOnly = true)
    public List<SharePostDto.CardResponse> getAllPost() {
		List<SharePost> postList = sharePostRepository.findAll(Sort.by(Direction.DESC, "createdDate"));
        return postList.stream().map(SharePostDto.CardResponse::new).collect(Collectors.toList());
    }
    
    //게시글 수정 
    @Override
    @Transactional
    public void modifyPost(Long postIdx, SharePostDto.Request dto) {
    	SharePost entity = sharePostRepository.findById(postIdx).orElseThrow(); 
    	entity.updatePost(dto.getTitle(), dto.getCategory(), dto.getDescr(), dto.getAddress(), dto.getImageIdx());
    }
    
    //게시글 삭제  
    @Override
    @Transactional
    public void removePost(Long postIdx) {
    	sharePostRepository.deleteById(postIdx);
    }
    
    //진행 유무 변경 
    @Override
    @Transactional
    public void modifyProgress(Long postIdx, boolean prog) {
    	SharePost entity = sharePostRepository.findById(postIdx).orElseThrow(); 
    	entity.updateProgress(prog);
    }
    

}
