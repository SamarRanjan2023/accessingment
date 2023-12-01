package com.employee.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.portal.dto.EmployeeDetail;
import com.employee.portal.dto.EmployeeDetailResponseDTO;
import com.employee.portal.dto.EmployeeRequestDTO;
import com.employee.portal.dto.EmployeeResponseDTO;
import com.employee.portal.error.InvalidEmployeeError;
import com.employee.portal.service.EmployeeService;
import com.employee.portal.util.EmployeValidatorUtil;

@RestController
public class EmployeeServiceController {
	@Autowired
	EmployeeService employeeService;

	@PostMapping("/employee/add")
	public ResponseEntity<EmployeeResponseDTO> addEmployee(EmployeeRequestDTO employeeRequest)
			throws InvalidEmployeeError {
		EmployeeResponseDTO response = new EmployeeResponseDTO();
		response.setMessage("SUCCESS");
		HttpStatus status = HttpStatus.OK;
		try {
			if (employeeRequest != null) {
				// Do the validation
				if (EmployeValidatorUtil.validateEmployee(employeeRequest)) {

					// store the data
					employeeService.save(employeeRequest);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(response, status);
	}

	@GetMapping("/employee/{empId}")
	@ResponseBody
	public ResponseEntity<EmployeeDetailResponseDTO> addgetEmployeeDetail(@PathVariable(name = "empId") Long empId)
			throws InvalidEmployeeError {
		EmployeeDetailResponseDTO response = new EmployeeDetailResponseDTO();
		response.setMessage("SUCCESS");
		HttpStatus status = HttpStatus.OK;
		try {
			if (empId != null && empId > 0) {
				// get the employee object
				EmployeeDetail empObj = employeeService.findById(empId);
				if (empObj != null) {
					response.setEmployeeDetail(empObj);
				} else {
					throw new Exception("Employee id not found.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(response, status);
	}
}
