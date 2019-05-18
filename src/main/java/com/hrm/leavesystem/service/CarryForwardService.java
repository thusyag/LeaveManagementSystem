package com.hrm.leavesystem.service;

import java.util.List;

import com.hrm.leavesystem.entity.CarryForward;

public interface CarryForwardService {

	boolean addCarryForward(CarryForward carryForward);

	List<CarryForward> viewCarryForward();

	Integer getCarryForwardDaysByUserId(Integer id);

}
