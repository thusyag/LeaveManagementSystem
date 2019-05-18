package com.hrm.leavesystem.service;

import java.util.List;

import com.hrm.leavesystem.entity.Role;
public interface RoleService {
	
	boolean addRole(Role role);

	List<Role> getAllRoles();

	boolean editRole(Role role, Integer id);
	
	Role getRoleById(int id);
	
	boolean deleteRole(Integer id);
}
