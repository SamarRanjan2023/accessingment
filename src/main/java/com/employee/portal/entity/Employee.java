package com.employee.portal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employee {

	@Id
	private Long empId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumbers;
	private String doj;
	private String monthlySalary;

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(String phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public String getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(String monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", phoneNumbers=" + phoneNumbers + ", doj=" + doj + ", monthlySalary="
				+ monthlySalary + "]";
	}

}