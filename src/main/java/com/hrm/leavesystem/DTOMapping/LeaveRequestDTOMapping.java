package com.hrm.leavesystem.DTOMapping;

import com.hrm.leavesystem.Dto.LeaveRequestDto;
import com.hrm.leavesystem.entity.LeaveRequest;


public class LeaveRequestDTOMapping {

	public static LeaveRequest LeaveRequestDTOToLeaveRequest(LeaveRequestDto leaveRequestDTO) {
		
		LeaveRequest leaveRequest=new LeaveRequest();
		
		leaveRequest.setStartDate(leaveRequestDTO.getStartDate());
		leaveRequest.setEndDate(leaveRequestDTO.getEndDate());
		leaveRequest.setLeaveDays(leaveRequestDTO.getLeaveDays());
		leaveRequest.setReason(leaveRequestDTO.getReason());
		
		
		return leaveRequest;
		
	}
}
