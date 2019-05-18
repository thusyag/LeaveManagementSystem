package com.hrm.leavesystem.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.hrm.leavesystem.entity.LeaveType;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer>{
	LeaveType findById(int id);
}
