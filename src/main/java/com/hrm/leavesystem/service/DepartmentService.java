package com.hrm.leavesystem.service;

import java.util.List;

import com.hrm.leavesystem.entity.Department;


public interface DepartmentService {
   boolean addDepartment(Department department);
   List<Department> getAllDepartment();
   boolean editDepartment(Department department, Integer id);
   boolean deleteDepartment(Integer id);
   Department getById(Integer id);
}
