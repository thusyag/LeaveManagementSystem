package com.hrm.leavesystem.controller;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.leavesystem.DTOMapping.LeaveRequestDTOMapping;
import com.hrm.leavesystem.Dto.LeaveRequestDto;
import com.hrm.leavesystem.Dto.LeaveRequestProcessDto;
import com.hrm.leavesystem.entity.LeaveRequest;
import com.hrm.leavesystem.entity.RejectLeaveRequest;
import com.hrm.leavesystem.entity.User;
import com.hrm.leavesystem.service.LeaveRequestService;
import com.hrm.leavesystem.service.LeaveService;
import com.hrm.leavesystem.service.LeaveTypeService;
import com.hrm.leavesystem.service.RejectLeaveRequestService;
import com.hrm.leavesystem.service.StatusService;
import com.hrm.leavesystem.service.UserService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class LeaveRequestController {

	@Autowired
	LeaveRequestService leaveRequestService;
	@Autowired
	LeaveService leaveService;
	@Autowired
	RejectLeaveRequestService rejectLeaveRequestService;
	@Autowired
	UserService userService;
	@Autowired
	LeaveTypeService leaveTypeService;
	@Autowired
	StatusService statusService;

	@PostMapping("/leaverequest")
	public boolean addLeaveRequestDTO(@RequestBody LeaveRequestDto leaveRequestDTO) {

		LeaveRequest leaveRequest = LeaveRequestDTOMapping.LeaveRequestDTOToLeaveRequest(leaveRequestDTO);

		leaveRequest.setUserId(userService.getUserById(leaveRequestDTO.getUserId()));
		leaveRequest.setLeaveTypeId(leaveTypeService.getLeaveTypeById(leaveRequestDTO.getLeaveTypeId()));
		leaveRequest.setStatusId(statusService.getStatusById(leaveRequestDTO.getStatusId()));

		leaveRequestService.addLeaveRequest(leaveRequest);
		// save record in leave request table
		if (leaveRequestService.addLeaveRequest(leaveRequest)) {

			leaveService.decreaseRemaingLeaveDays(leaveRequest.getLeaveDays(), leaveRequestDTO.getUserId(),
					leaveRequestDTO.getLeaveTypeId());
			return true;
		}

		return false;
	}

	@GetMapping("/leaveRequestModel")
	public LeaveRequestDto getMockLeaveRequestDTO() {
		LeaveRequestDto obj = new LeaveRequestDto();
		return obj;

	}
	
	@GetMapping("/leaveApprovedto")
	public LeaveRequestProcessDto getMockLeaveApproveDTO() {
		LeaveRequestProcessDto obj= new LeaveRequestProcessDto();
		return obj;

	}

	@GetMapping("/leaverequest")
	public List<LeaveRequest> getData() {
		return leaveRequestService.getData();
	}
	
	@GetMapping("/leaverequest/sort")
    public List<LeaveRequest> getDataBySort() {
        return leaveRequestService.getDataBySort();
    }

	
	@DeleteMapping("/leaverequest/deleteRequest/{id}")
	public boolean deleteLeaveRequest(@PathVariable("id") int id) {
		LeaveRequest leaveRequest = leaveRequestService.findLeaveRequestById(id);
		leaveService.increaseRemaingLeaveDays(leaveRequest.getLeaveDays(), leaveRequest.getUserId().getId(),
				leaveRequest.getLeaveTypeId().getId());
		leaveRequestService.deleteLeaveRequest(id);

		return true;
	}

	
	
	@PostMapping("/leaverequest/leaveapprove")
	public HttpStatus approveLeaveRequest(@RequestBody LeaveRequestProcessDto lvRePrObj) {
		boolean sucessStatus = leaveRequestService.editLeaveRequestStatus(lvRePrObj.getLeaveRequestId(),lvRePrObj.getStatusId());
		boolean successApproval = leaveRequestService.editLeaveRequestApproval(lvRePrObj.getLeaveRequestId(), lvRePrObj.getProcessedBy());
	
		if (sucessStatus && successApproval) {
			return HttpStatus.CREATED;

		}

		return HttpStatus.BAD_REQUEST;

	}

	/*
	 * Set the Status Id:1 for approve leave request
	 */
	@PostMapping("/leaverequest/rejectleave")
	public HttpStatus addRejectLeave(@RequestBody LeaveRequestProcessDto lvRePrObj) {
		boolean rejectStatus = leaveRequestService.editLeaveRequestStatus(lvRePrObj.getLeaveRequestId(),lvRePrObj.getStatusId());
		boolean rejectBy = leaveRequestService.editLeaveRequestApproval(lvRePrObj.getLeaveRequestId(), lvRePrObj.getProcessedBy());
		
		LeaveRequest leaveRequest = leaveRequestService.findLeaveRequestById(lvRePrObj.getLeaveRequestId());
		leaveService.increaseRemaingLeaveDays(leaveRequest.getLeaveDays(), leaveRequest.getUserId().getId(),
				leaveRequest.getLeaveTypeId().getId());
		
		RejectLeaveRequest rejectLeaveRequest=new RejectLeaveRequest();
		rejectLeaveRequest.setLeaveRequestId(leaveRequest);
		User rejectByuser=userService.getUserById(lvRePrObj.getProcessedBy());
		rejectLeaveRequest.setRejectedBy(rejectByuser);
		rejectLeaveRequest.setRejectReason(lvRePrObj.getRejectreason());
		
		boolean saveRejectDetails=rejectLeaveRequestService.addRejectLeaveRequest(rejectLeaveRequest);
	
		
		if (rejectStatus && rejectBy && saveRejectDetails) {
			return HttpStatus.ACCEPTED;
		}
		return HttpStatus.BAD_REQUEST;
	}

	// get details of leave request by user id
	@GetMapping("/leaverequest/user/{userId}")
	public ResponseEntity<List<LeaveRequest>> findLeaveRequestByUserId(@PathVariable("userId") Integer id) {		
		List<LeaveRequest> leaveRequsetDetails = leaveRequestService.findByUserId(id);
		return new ResponseEntity<>(leaveRequsetDetails, HttpStatus.OK);
	}

	// leave request details find by date
	@GetMapping("/leaverequest/{date}")
	public List<LeaveRequest> getDates(@PathVariable("date") @DateTimeFormat(iso = ISO.DATE_TIME) ZonedDateTime date) {
		return leaveRequestService.findByDate(date);
	}

}
