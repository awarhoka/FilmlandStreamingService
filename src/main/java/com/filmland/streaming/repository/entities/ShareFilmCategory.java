package com.filmland.streaming.repository.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the share_film_category database table.
 * 
 */
@Entity
@Table(name = "share_film_category")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class ShareFilmCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ShareFilmCategoryId id;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = UserInfo.class)
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	private UserInfo userInfo;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = UserInfo.class)
	@JoinColumn(name = "cust_id", nullable = false, insertable = false, updatable = false)
	private UserInfo custInfo;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = FilmCategory.class)
	@JoinColumn(name = "film_category_id", nullable = false, insertable = false, updatable = false)
	private FilmCategory filmCategory;

}