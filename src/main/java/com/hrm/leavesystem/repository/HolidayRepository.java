package com.hrm.leavesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrm.leavesystem.entity.Holiday;

public interface HolidayRepository extends JpaRepository<Holiday, Integer> {

}
