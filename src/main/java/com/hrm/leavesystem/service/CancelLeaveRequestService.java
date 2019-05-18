package com.hrm.leavesystem.service;

import java.util.List;

import com.hrm.leavesystem.entity.CancelLeaveRequest;

public interface CancelLeaveRequestService {
	boolean addCancelLeaveRequest(CancelLeaveRequest cancelLeaveRequest);

	List<CancelLeaveRequest> getLeaveRequest();

	boolean editCancelRequestApproval(int leaveRequsetId, int userId);
}
