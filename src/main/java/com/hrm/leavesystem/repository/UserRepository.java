package com.hrm.leavesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrm.leavesystem.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserName(String userName);
	
}
