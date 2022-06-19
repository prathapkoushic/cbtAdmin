package com.ttipl.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author gopi prasad garimidi
 *
 */
@Entity
@Table(name = "role")
public class Role {
	@Id
	@Column(name = "role_id")
	private int roleId;
	@Column(name = "role")
	private String role;
	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
}
