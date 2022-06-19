package com.ttipl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttipl.pojo.Post;
import com.ttipl.repository.PostRepository;

@Service

public class PostService {

	@Autowired
	private PostRepository postRepository;

	public Post savePost(Post postBean) {
		return postRepository.save(postBean);
	}

	public List<Post> getPostResult() {
		return postRepository.findAll();
	}

	public Post getPostResultRandomOne() {
		return postRepository.findAllRandomOne();
	}
	/*
	 * public PostBean updatePostById(int id) { return
	 * postRepository.updateByPostId(id); }
	 */

	public int deletePostById(int id) {
		return postRepository.deleteByPostId(id);
	}

	public Post findByid(int id) {
		return postRepository.findByPostId(id);
	}
}
