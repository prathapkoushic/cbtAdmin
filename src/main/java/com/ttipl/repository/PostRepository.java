package com.ttipl.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ttipl.pojo.Post;

@Repository

public interface PostRepository extends CrudRepository<Post, Integer> {

	public List<Post> findAll();

	public Post findByPostId(int id);
	@Query(value="select * from post  limit 1",nativeQuery=true)
	public Post findAllRandomOne();

	@Transactional
	public int deleteByPostId(int id);

}
