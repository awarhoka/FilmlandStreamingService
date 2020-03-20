package com.filmland.streaming.service;

import com.filmland.streaming.exception.DataNotFoundException;
import com.filmland.streaming.model.ShareSubscription;
import com.filmland.streaming.model.UserDto;

public interface UserService {

	UserDto findUserByMail(String email) throws DataNotFoundException;
	
	UserDto findUserByUsername(String username) throws DataNotFoundException;
	
	boolean saveSubscription(String email, String availableCategory) throws Exception;
	
	boolean shareSubscription(ShareSubscription shareSubscription) throws Exception;
}
