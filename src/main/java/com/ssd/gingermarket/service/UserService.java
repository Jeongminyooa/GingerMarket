package com.ssd.gingermarket.service;

import org.springframework.stereotype.Service;

import com.ssd.gingermarket.dto.UserDto;

@Service
public interface UserService {
	//회원 등록
	public Long addUser(UserDto.Request dto);
	
	//회원 정보 수정
	public void modifyUser(Long userIdx,UserDto.Request dto);
	
	//회원 탈퇴
	public void removeUser(Long userIdx);
	
	public UserDto.Info getUser(String userId, String password);
	
	public UserDto.Info getUser(Long userIdx);
	
	public int userIdCheck(String userId) throws Exception;
	public int nameCheck(String name) throws Exception;
	
}
