package com.ttipl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttipl.pojo.PostCategory;
import com.ttipl.repository.PostCategoryRepo;

@Service
public class PostCategoryService {

	@Autowired
	private PostCategoryRepo postCategoryRepo;

	public PostCategory savePostCategory(PostCategory postCategory) {
		return postCategoryRepo.save(postCategory);
	}

	public PostCategory findByPostCategoryId(Integer id) {
		return postCategoryRepo.findByPostCategoryId(id);
	}

	public List<PostCategory> findAll() {
		return postCategoryRepo.findAll();

	}

}
