package com.filmland.streaming.service;

import java.util.List;

import com.filmland.streaming.exception.DataNotFoundException;
import com.filmland.streaming.model.FilmCategoryDto;

public interface FilmCategoryService {

	List<FilmCategoryDto> retriveAll() throws DataNotFoundException;

	FilmCategoryDto findByName(String name) throws DataNotFoundException;
	
	
}