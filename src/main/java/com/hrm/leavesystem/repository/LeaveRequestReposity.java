/**
 * 
 */
package com.hrm.leavesystem.repository;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hrm.leavesystem.entity.LeaveRequest;

public interface LeaveRequestReposity extends JpaRepository<LeaveRequest, Integer> {
	

	@Query("select lr from LeaveRequest as lr where lr.startDate <= ?1 AND lr.endDate >=?1")
	public List<LeaveRequest> findByDate(ZonedDateTime abc);

	@Query("select lr from LeaveRequest as lr where lr.userId.id = ?1 order by lr.updatedAt desc")
	List<LeaveRequest> findByUser(Integer id);
	
	@Query("select lr from LeaveRequest as lr order by lr.updatedAt desc")
	List<LeaveRequest> sortByUpdatedAt();
}
