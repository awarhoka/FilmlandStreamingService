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
public class ShareSubscription implements Serializable {

	private static final long serialVersionUID = -6620640844675596637L;

	private String email;
	private String customer;
	private String subscribedCategory;
}
