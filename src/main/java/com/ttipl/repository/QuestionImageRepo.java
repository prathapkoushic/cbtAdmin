package com.ttipl.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ttipl.pojo.QuestionImages;

@Repository
public interface QuestionImageRepo extends CrudRepository<QuestionImages, Integer> {
	@Query("select i.Image from QuestionImages i where i.imageId=?1 ")
	String findByImageId(int id);
}
