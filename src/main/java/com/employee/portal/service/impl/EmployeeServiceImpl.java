package com.employee.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.portal.dao.EmployeeRepository;
import com.employee.portal.dto.EmployeeDetail;
import com.employee.portal.dto.EmployeeRequestDTO;
import com.employee.portal.entity.Employee;
import com.employee.portal.service.EmployeeService;
import com.employee.portal.util.EmployeValidatorUtil;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<EmployeeDetail> findAll() {
		List<EmployeeDetail> empList = new ArrayList<>();
		List<Employee> allEmp = employeeRepository.findAll();

		for (Employee employee : allEmp) {
			empList.add(EmployeValidatorUtil.mapEmployeeObject(employee));
		}
		return empList;
	}

	@Override
	public EmployeeDetail findById(long id) {
		Employee emp = employeeRepository.findById(id);
		EmployeeDetail empDetail = EmployeValidatorUtil.mapEmployeeObject(emp);
		return empDetail;
	}

	@Override
	public Employee save(EmployeeRequestDTO employee) {
		Employee emp = new Employee();
		emp.setEmpId(employee.getEmpId());
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setEmail(employee.getEmail());
		emp.setMonthlySalary(employee.getMonthlySalary());
		emp.setPhoneNumbers(employee.getPhoneNumbers());
		emp.setDoj(employee.getDoj());

		return employeeRepository.save(emp);
	}

	@Override
	public void deleteById(long id) {
		employeeRepository.deleteById(id);
	}
}
