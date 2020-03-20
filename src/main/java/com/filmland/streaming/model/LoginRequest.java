package com.filmland.streaming.model;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class LoginRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;

	private String email;
	private String password;
}