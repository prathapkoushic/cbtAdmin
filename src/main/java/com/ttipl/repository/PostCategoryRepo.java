package com.ttipl.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ttipl.pojo.PostCategory;

@Repository
public interface PostCategoryRepo extends CrudRepository<PostCategory, Integer> {
	PostCategory findByPostCategoryId(Integer id);

	List<PostCategory> findAll();
}
