package com.filmland.streaming.repository.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the film_category database table.
 * 
 */
@Entity
@Table(name = "film_category")
//@NamedQuery(name = "FilmCategory.findAll", query = "SELECT f FROM FilmCategory f")
@Getter
@Setter
//@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class FilmCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "film_category_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer filmCategoryId;

	@Column(name = "availablecontent", nullable = false)
	private int availableContent;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "price", nullable = false)
	private BigDecimal price;

	// bi-directional many-to-one association to UserFilmCategory

	@OneToMany(mappedBy = "filmCategory", /* fetch = FetchType.LAZY, */ cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserFilmCategory> userFilmCategories;

}