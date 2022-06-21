package com.ssd.gingermarket.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssd.gingermarket.domain.User;
import com.ssd.gingermarket.dto.UserDto;
import com.ssd.gingermarket.dto.UserDto.Info;
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
	public void modifyUser(Long userIdx,UserDto.Request dto) {
		User user = userRepository.findById(userIdx).orElseThrow(); 
		user.updateUser(dto.getUserId(), dto.getPassword(), dto.getName(),
				dto.getPhone1()+dto.getPhone2()+dto.getPhone3(), 
				dto.getAddr(), dto.getItems()[0], dto.getItems()[1], dto.getItems()[2]);
	}
	@Override
	@Transactional
	public void removeUser(Long userIdx) {
		userRepository.deleteById(userIdx);
	}
	
	@Override
	@Transactional
	public UserDto.Info getUser(String userId, String password) {
		User user = userRepository.findByUserIdAndPassword(userId, password);
		if(user==null) 
			return new UserDto.Info(null,null,null,null,null,null, null,null,null,null);
		
		return new UserDto.Info(user.getUserIdx(),user.getUserId(),user.getPassword(),
				user.getName(),user.getPhone(),user.getAddress(), 
				user.getItem1(),user.getItem2(),user.getItem3(),user.getImage());
		
	}
	@Override
	@Transactional
	public UserDto.Info getUser(Long userIdx) {
		User user = userRepository.findById(userIdx).get();
		return new UserDto.Info(user.getUserIdx(),user.getUserId(),user.getPassword(),
				user.getName(),user.getPhone(),user.getAddress(), 
				user.getItem1(),user.getItem2(),user.getItem3(),user.getImage());
	}
	
	@Override
	public int userIdCheck(String userId) throws Exception{
		if(userRepository.findByUserId(userId)!=null)
			return 1;
		return 0;
	}
	
	@Override
	public int nameCheck(String name) throws Exception{
		if(userRepository.findByName(name)!=null)
			return 1;
		return 0;
	}
	
}
