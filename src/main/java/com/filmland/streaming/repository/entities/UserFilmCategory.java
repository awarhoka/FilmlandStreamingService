package com.filmland.streaming.repository.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the user_film_category database table.
 * 
 */
@Entity
@Table(name = "user_film_category")
@Getter
@Setter
@NoArgsConstructor
public class UserFilmCategory implements Serializable {
	private static final long serialVersionUID = 1L;

 
	@EmbeddedId
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UserFilmCategoryId id;

	@Column(name = "startdate", nullable = false)
	private Date startDate;

	// bi-directional many-to-one association to FilmCategory
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = FilmCategory.class)
	// @MapsId("filmCategoryId")
	@JoinColumn(name = "film_category_id", nullable = false, insertable = false, updatable = false)
	private FilmCategory filmCategory;

	// bi-directional many-to-one association to UserInfo

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = UserInfo.class)
	// @MapsId("userId")
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	private UserInfo userInfo;

	public UserFilmCategory(UserInfo userInfo, Date startDate) {
		this.userInfo = userInfo;
		this.startDate = startDate;
	}

}