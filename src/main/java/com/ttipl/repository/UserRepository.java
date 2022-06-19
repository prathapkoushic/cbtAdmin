package com.ttipl.repository;
/**
 * 
 * @author gopi prasad garimidi
 *
 */

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ttipl.pojo.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	User findByUsernameAndPassword(String username, String password);
	User findByUsername(String username);
	User findByUserId(int userId);
}
