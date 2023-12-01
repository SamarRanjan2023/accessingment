package com.employee.portal.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.employee.portal.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	List<Employee> findByFirstName(String firstName);
	List<Employee> findByLastName(String lastName);
	Employee findById(long id);
	List<Employee> findAll(); 
}
