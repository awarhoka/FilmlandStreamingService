package com.filmland.streaming.repository.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * The persistent class for the user_info database table.
 * 
 */
@Entity
@Table(name = "user_info")
//@NamedQuery(name = "UserInfo.findAll", query = "SELECT u FROM UserInfo u")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "username", nullable = false)
	private String username;

	// bi-directional many-to-one association to UserFilmCategory
	@OneToMany(mappedBy = "userInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserFilmCategory> userFilmCategories = new ArrayList<>();
	
	//private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

}