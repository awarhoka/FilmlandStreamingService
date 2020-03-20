package com.filmland.streaming.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.filmland.streaming.repository.entities.FilmCategory;

@Repository
public interface FilmCategoryRepository extends JpaRepository<FilmCategory, Long> {
	
	FilmCategory findByName(@Param("name") String name);
}
