package com.ttipl.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ttipl.pojo.DescriptiveQuestionImages;

@Repository
public interface DescriptiveQuestionImagesRepo extends CrudRepository<DescriptiveQuestionImages, Integer>
{
	@Query("select i.Image from DescriptiveQuestionImages i where i.imageId=?1 ")
	String findByImageId(int id);
}
