package com.employee.portal.service;

import java.util.List;

import com.employee.portal.dto.EmployeeDetail;
import com.employee.portal.dto.EmployeeRequestDTO;
import com.employee.portal.entity.Employee;

public interface EmployeeService {

	List<EmployeeDetail> findAll();

	EmployeeDetail findById(long id);

	Employee save(EmployeeRequestDTO employee);

	void deleteById(long id);

}