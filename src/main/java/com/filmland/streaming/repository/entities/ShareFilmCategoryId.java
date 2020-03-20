package com.filmland.streaming.repository.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class ShareFilmCategoryId implements Serializable {

	private static final long serialVersionUID = 8070175211283886850L;

	@NonNull
	@Column(name = "user_id")
	private Integer userId;
	
	@NonNull
	@Column(name = "cust_id")
	private Integer custId;
	
	@NonNull
	@Column(name = "film_category_id")
	private Integer filmCategoryId;

}
