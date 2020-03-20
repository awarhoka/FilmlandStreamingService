package com.filmland.streaming.model;

import java.io.Serializable;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserDto implements Serializable {

	private static final long serialVersionUID = 8379964115219043690L;
	private String userId;
	private String username;
	private String email;
	private String password;
	private List<FilmCategoryDto> userFilmCategories;

}
