package com.ttipl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttipl.pojo.LoginBean;
import com.ttipl.pojo.Role;
import com.ttipl.pojo.User;
import com.ttipl.repository.LoginRepo;
import com.ttipl.repository.RoleRepository;
import com.ttipl.repository.UserRepository;

@Service
public class LoginService {
	@Autowired
	private LoginRepo loginRepository;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;

	public void insert(LoginBean login) {
		loginRepository.save(login);

	}

	public List<LoginBean> get() {
		return (List<LoginBean>) loginRepository.findAll();
	}

	public List<LoginBean> getdata(String username, String password) {
		return loginRepository.findByUsernameAndPassword(username, password);
	}

	public User userLogin(String username, String password) {
		return userRepo.findByUsernameAndPassword(username, password);
	}

	public User findUser(String username) {
		return userRepo.findByUsername(username);
	}
	public User findUserByUserId(int userId) {
		return userRepo.findByUserId(userId);
	}

	public User saveUser(User user) {
		return userRepo.save(user);
	}

	public List<Role> findAllRoles() {
		return roleRepo.findAll();
	}

	public Role findAllRoleId(int roleId) {
		return roleRepo.findByRoleId(roleId);
	}

}
