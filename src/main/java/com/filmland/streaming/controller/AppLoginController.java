package com.filmland.streaming.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.filmland.streaming.config.AppRequestFilter;
import com.filmland.streaming.model.LoginRequest;
import com.filmland.streaming.model.MessageResponse;
import com.filmland.streaming.repository.entities.UserInfo;
import com.filmland.streaming.util.JwtTokenUtil;

@RestController
@CrossOrigin
public class AppLoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {
		jwtTokenUtil.setSecret(loginRequest.getPassword());
		MessageResponse message = new MessageResponse();
		try {
			authenticate(loginRequest.getEmail(), loginRequest.getPassword());

			final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());

			final String token = jwtTokenUtil.generateToken(userDetails);
			message.setStatus("Login successful");
			message.setMessage("You are Logged in Successfully !");
			HttpHeaders headers = new HttpHeaders();
			headers.add("Set-Cookie", AppRequestFilter.ACCESS_TOKEN_COOKIE_NAME + "=" + token + "; Max-Age="
					+ JwtTokenUtil.JWT_TOKEN_VALIDITY + " ; Path=/; Secure; HttpOnly");
			return ResponseEntity.ok().headers(headers).body(message);
		} catch (Exception e) {
			message.setStatus("Login failed");
			message.setMessage("Unable to Login!");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
		}

	}

	private void authenticate(String email, String password) throws Exception {
		Objects.requireNonNull(email);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	/*
	 * @GetMapping("/userinfo") public UserInfo userInfo(@AuthenticationPrincipal
	 * UserInfo principal) { return new UserInfo(principal.getEmail()); }
	 */
}
