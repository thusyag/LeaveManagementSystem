package com.hrm.leavesystem.service;

import java.time.ZonedDateTime;
import java.util.List;

import com.hrm.leavesystem.entity.LeaveRequest;

public interface LeaveRequestService {

	
	boolean addLeaveRequest(LeaveRequest leaveRequest);

	
	boolean deleteLeaveRequest(int id);

	
	List<LeaveRequest> getData();

	
	LeaveRequest findLeaveRequestById(int id);

	
	boolean editLeaveRequestStatus(int id, int statusId);

	// edit leave request Approved
	boolean editLeaveRequestApproval(int id, int userId);

	public List<LeaveRequest> findByUserId(Integer id);

	
	public List<LeaveRequest> findByDate(ZonedDateTime date);
	
	public List<LeaveRequest> getDataBySort();

}
