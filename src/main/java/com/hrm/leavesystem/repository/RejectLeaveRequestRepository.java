package com.hrm.leavesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrm.leavesystem.entity.RejectLeaveRequest;

public interface RejectLeaveRequestRepository extends JpaRepository<RejectLeaveRequest, Integer>{
	

}
