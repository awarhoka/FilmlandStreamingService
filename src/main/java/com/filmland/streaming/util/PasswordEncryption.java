package com.filmland.streaming.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryption {

	public PasswordEncryption() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		 	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			    String rawPwd = "test4";    
			    String encodedPwd = encoder.encode(rawPwd);
			    System.out.println("Encoded Password : " + encodedPwd);
	}
}
