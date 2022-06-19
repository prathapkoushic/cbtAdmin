package com.ttipl.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "post_category")
public class PostCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_category_id", unique = true, nullable = false)
	private Integer postCategoryId;

	@Column(name = "post_category_name", unique = true)
	private String postCategoryName;

	public Integer getPostCategoryId() {
		return postCategoryId;
	}

	public void setPostCategoryId(Integer postCategoryId) {
		this.postCategoryId = postCategoryId;
	}

	public String getPostCategoryName() {
		return postCategoryName;
	}

	public void setPostCategoryName(String postCategoryName) {
		this.postCategoryName = postCategoryName;
	}

}
