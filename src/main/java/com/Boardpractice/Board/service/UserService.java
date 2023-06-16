package com.Boardpractice.Board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Boardpractice.Board.entity.User;
import com.Boardpractice.Board.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public void saveUser(User user) {
		
		userRepository.save(user);
	}
	
	public User findByUserNameAndUserPw(String userName, String userPw) {
		
		return userRepository.findByUserNameAndUserPw(userName, userPw);
	}
}
