package com.ssd.gingermarket.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssd.gingermarket.domain.User;
import com.ssd.gingermarket.dto.UserDto;
import com.ssd.gingermarket.repository.UserRepository;

import lombok.RequiredArgsConstructor;

//@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	
	@Override
	@Transactional
	public Long addUser(UserDto.Request dto) {
		return userRepository.save(dto.toEntity()).getUserIdx();
		
	}
	@Override
	public void updateUser(Long userIdx,UserDto.Request dto) {
		User user = userRepository.findById(userIdx).orElseThrow(); 
		user.updateUser(dto.getUserId(), dto.getPassword(), dto.getName(),
				dto.getPhone1()+dto.getPhone2()+dto.getPhone3(), 
				dto.getAddr(), dto.getItems()[0], dto.getItems()[1], dto.getItems()[2]);
	}
	
	@Override
	public void removeUser(Long userIdx) {
		userRepository.deleteById(userIdx);
	}
	
	@Override
	public User getUser(String userId, String password) {
		return userRepository.findByUserIdAndPassword(userId, password);
	}

	@Override
	public User getUser(Long userIdx) {
		return userRepository.findByUserIdx(userIdx);
	}
	
}
