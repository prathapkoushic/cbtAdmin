package com.ttipl.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ttipl.pojo.Role;

/**
 * 
 * @author gopi prasad garimidi
 *
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

	List<Role> findAll();

	Role findByRoleId(int id);
}
