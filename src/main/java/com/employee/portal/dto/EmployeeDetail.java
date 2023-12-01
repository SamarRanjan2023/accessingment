package com.employee.portal.dto;

public class EmployeeDetail {
	private String empId;
	private String firstName;
	private String lastName;
	private String anualSalary;
	private String taxAmount;
	private String cessTax;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
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

	public String getAnualSalary() {
		return anualSalary;
	}

	public void setAnualSalary(String anualSalary) {
		this.anualSalary = anualSalary;
	}

	public String getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getCessTax() {
		return cessTax;
	}

	public void setCessTax(String cessTax) {
		this.cessTax = cessTax;
	}

	@Override
	public String toString() {
		return "EmployeeDetailResponseDTO [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", anualSalary=" + anualSalary + ", taxAmount=" + taxAmount + ", cessTax=" + cessTax + "]";
	}

}
