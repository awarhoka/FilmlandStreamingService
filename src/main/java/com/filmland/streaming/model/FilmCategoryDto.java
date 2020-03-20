package com.filmland.streaming.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class FilmCategoryDto implements Serializable {

	private static final long serialVersionUID = 6287983226581960161L;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String filmCategoryId;
	private String name;
	@JsonInclude(Include.NON_NULL)
	private String availableContent;
	@JsonInclude(Include.NON_NULL)
	private String remainingContent;
	private String price;
	@JsonInclude(Include.NON_NULL)
	private String startDate;

}
