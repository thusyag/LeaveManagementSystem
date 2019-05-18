package com.hrm.leavesystem.service;

import java.util.List;

import com.hrm.leavesystem.Dto.LeaveStatisticsDto;
import com.hrm.leavesystem.entity.Leave;

public interface LeaveService {

	boolean addLeaveAllocation(String userName);
	boolean deleteLeave(Integer userId);

	
	float increaseRemaingLeaveDays(float numOfDays, int userID, int leaveTypeId);

	
	float decreaseRemaingLeaveDays(float numOfDays, int userID, int leaveTypeId);
	
	Leave findRemaingDays(int uid, int lid);
	 
	Iterable<LeaveStatisticsDto> findRemaingDays(Integer uid);

}
