package com.hrm.leavesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrm.leavesystem.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findById(int id);
	Role findByRoleName(String roleName);
}
