package com.filmland.streaming.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.filmland.streaming.repository.UserRepository;
import com.filmland.streaming.repository.entities.UserInfo;

@Service("userDetailsService")
public class JwtUserDetailsService implements UserDetailsService {
	
	static final Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserInfo user = userRepository.findUserByEmail(email);
		logger.info("User : {}", user);
		if(user==null){
			logger.info("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
		/*Getting User information based on Email*/	
		return new User(user.getEmail(), user.getPassword(), 
				 true, true, true, true, this.getGrantedAuthorities(user));
	}
	/*User Roles are hardcoded TO-DO*/
	private List<GrantedAuthority> getGrantedAuthorities(UserInfo user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		String roles [] = {"USER","ADMIN"};
		List<String> rolesList = java.util.Arrays.asList(roles);
		for(String role : rolesList){
			logger.info("UserProfile : {}", role);
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
		}
		logger.info("authorities : {}", authorities);
		return authorities;
	}

}
