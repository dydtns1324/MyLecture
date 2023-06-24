package com.Boardpractice.Board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Boardpractice.Board.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUserNameAndUserPw(String userName, String userPw);
}
