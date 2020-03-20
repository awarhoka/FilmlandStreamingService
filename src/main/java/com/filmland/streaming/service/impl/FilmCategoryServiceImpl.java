package com.filmland.streaming.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmland.streaming.exception.DataNotFoundException;
import com.filmland.streaming.model.FilmCategoryDto;
import com.filmland.streaming.repository.FilmCategoryRepository;
import com.filmland.streaming.repository.entities.FilmCategory;
import com.filmland.streaming.service.FilmCategoryService;

@Service("filmCategoryService")
public class FilmCategoryServiceImpl implements FilmCategoryService {

	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@Autowired
	FilmCategoryRepository repository;

	@Autowired
	private Mapper dozerBeanMapper;

	@Override
	public List<FilmCategoryDto> retriveAll() throws DataNotFoundException {
		List<FilmCategory> filmCategoryList = repository.findAll();
		if (filmCategoryList.size() > 0) {
			return this.mapListObjectToNewListObject(filmCategoryList, FilmCategoryDto.class);
		} else {
			throw new DataNotFoundException("No Data exist ");
		}
	}

	@Override
	public FilmCategoryDto findByName(String name) throws DataNotFoundException {
		FilmCategory filmCategory = repository.findByName(name);
		if (null != filmCategory) {
			return dozerBeanMapper.map(filmCategory, FilmCategoryDto.class);
		} else {
			throw new DataNotFoundException("No Data exist ");
		}
	}

	public <T, S> List<T> mapListObjectToNewListObject(List<S> srcObjects, Class<T> newObjectClass) {
		final List<T> newObjects = new ArrayList<T>();
		for (S srcObj : srcObjects) {
			newObjects.add(dozerBeanMapper.map(srcObj, newObjectClass));
		}
		return newObjects;
	}


}
