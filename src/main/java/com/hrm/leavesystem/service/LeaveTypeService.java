package com.hrm.leavesystem.service;

import java.util.List;

import com.hrm.leavesystem.entity.LeaveType;

public interface LeaveTypeService {

	List<LeaveType> getLeaveType();

	boolean editLeaveType(LeaveType leaveType, Integer id);

	boolean deleteLeaveType(Integer id);

	boolean addLeaveType(LeaveType leavetype);
	
	LeaveType getLeaveTypeById(int id);
	
	
}
