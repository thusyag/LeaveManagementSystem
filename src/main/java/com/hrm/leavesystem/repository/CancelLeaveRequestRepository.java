package com.hrm.leavesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrm.leavesystem.entity.CancelLeaveRequest;
import com.hrm.leavesystem.entity.LeaveRequest;

public interface CancelLeaveRequestRepository extends JpaRepository<CancelLeaveRequest, Integer> {
	CancelLeaveRequest findByLeaveRequestId(LeaveRequest id);
}
