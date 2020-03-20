package com.filmland.streaming.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class SubscribeFilmCategory implements Serializable {

	private static final long serialVersionUID = -2833202527594522336L;
	private String email;
    private String availableCategory;   
}
