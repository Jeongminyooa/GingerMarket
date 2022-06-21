package com.ssd.gingermarket.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssd.gingermarket.domain.Image;
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
	@Transactional
	public void modifyUser(Long userIdx,UserDto.Response dto) {
		User user = userRepository.findById(userIdx).orElseThrow();
		
		String phone = dto.getPhone1() + dto.getPhone2() + dto.getPhone3();
		
		// 비밀번호는 미리보기로 주어지지 않아서 변경하지 않으면 공백이 들어올 수 있음.
		if(!dto.getPassword().equals("")) {
			user.updatePassword(dto.getPassword());
		}
		
		user.updateUser(dto.getName(),
				phone, 
				dto.getAddress(),
				dto.getItems());
	}
	
	@Override
	@Transactional
	public void modifyUserImage(Long userIdx, Image img) {
		User user = userRepository.findById(userIdx).orElseThrow();
		
		user.updateProfileImage(img);
	}
	
	@Override
	@Transactional
	public void removeUser(Long userIdx) {
		userRepository.deleteById(userIdx);
	}
	
	@Override
	@Transactional
	public User getUser(String userId, String password) {
		return userRepository.findByUserIdAndPassword(userId, password);
	}

	@Override
	@Transactional
	public User getUser(Long userIdx) {
		return userRepository.findById(userIdx).get();
	}
	
	@Override
	@Transactional(readOnly = true)
	public UserDto.Response getUserInfo(Long userIdx) {
		User userEntity = userRepository.findById(userIdx).orElseThrow();
		
		String phone = userEntity.getPhone();
		return new UserDto.Response(userEntity);
	}
	
}
