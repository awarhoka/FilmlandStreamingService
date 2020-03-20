package com.filmland.streaming.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.filmland.streaming.repository.entities.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
	
	UserInfo findUserByEmail(@Param("email") String email);
	
	UserInfo findUserByUsername(@Param("username") String username);
	
}

