package com.filmland.streaming.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filmland.streaming.repository.entities.ShareFilmCategory;
@Repository
public interface ShareFilmCategoryRepository extends JpaRepository<ShareFilmCategory, Long> {

}
