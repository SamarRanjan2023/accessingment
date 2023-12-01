package com.employee.portal.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.employee.portal.dto.EmployeeDetail;
import com.employee.portal.dto.EmployeeRequestDTO;
import com.employee.portal.entity.Employee;
import com.employee.portal.error.InvalidEmployeeError;

public class EmployeValidatorUtil {
	public static boolean validateEmployee(EmployeeRequestDTO employee) throws InvalidEmployeeError {
		if (employee != null) {
			if (employee.getEmpId() != null && employee.getEmpId() > 0
					&& StringUtils.isNoneBlank(employee.getDoj(), employee.getEmail(), employee.getFirstName(),
							employee.getLastName(), employee.getMonthlySalary(), employee.getPhoneNumbers())) {
				if (!StringUtils.isAlpha(employee.getFirstName())) {
					throw new InvalidEmployeeError("Invalid emplyee first name.");
				} else if (!StringUtils.isAlpha(employee.getLastName())) {
					throw new InvalidEmployeeError("Invalid emplyee last name.");
				} else if (!validateEmail(employee.getEmail())) {
					throw new InvalidEmployeeError("Invalid emplyee email id.");
				} else if (!validatePhoneNumber(employee.getPhoneNumbers())) {
					throw new InvalidEmployeeError("Invalid emplyee mobile number.");
				} else if (!isValidDOJ(employee.getDoj())) {
					throw new InvalidEmployeeError("Invalid emplyee DOJ value'.");
				}
			} else {
				throw new InvalidEmployeeError("One of the input Field is not correct.");
			}
		}

		return true;
	}

	private static boolean isValidDOJ(String dateStr) {
		try {
			LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);// '2011-12-03'
		} catch (DateTimeParseException e) {
			return false;
		}
		return true;
	}

	private static boolean validateEmail(String email) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	private static boolean validatePhoneNumber(String phoneNumbers) {
		String[] numbers = StringUtils.split(phoneNumbers, ",");

		for (String mobileNo : numbers) {
			if (!StringUtils.isNumeric(mobileNo)) {
				return false;
			}
		}

		return true;
	}

	public static EmployeeDetail mapEmployeeObject(Employee employee) {

		EmployeeDetail empDetail = null;
		if (employee != null) {
			empDetail = new EmployeeDetail();
			empDetail.setEmpId(employee.getEmpId().toString());
			empDetail.setFirstName(employee.getFirstName());
			empDetail.setLastName(employee.getLastName());
			empDetail = calculateTax(empDetail, employee);
		}

		return empDetail;
	}

	private static EmployeeDetail calculateTax(EmployeeDetail empDetail, Employee employee) {
		int totalDays = 0;
		int totalMonth = 0;
		int monthlySalary = Integer.parseInt(employee.getMonthlySalary());
		long anualSalary = 0;
		long toatalIncomeTax = 0;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String joinDate = employee.getDoj();

		// convert String to LocalDate
		LocalDate localJoinDate = LocalDate.parse(joinDate, formatter);
		LocalDate todayDate = LocalDate.now();
		int joinMonth = localJoinDate.getMonthValue();
		int dayOftheMonth = localJoinDate.getDayOfMonth();

		// calculate number of months/days for same year joined same year
		if (localJoinDate.getYear() == todayDate.getYear() && joinMonth > 3) {
			if (dayOftheMonth > 1) {
				// calculate number of days
				totalDays = localJoinDate.lengthOfMonth() - dayOftheMonth + 1;
				totalMonth = 15 - joinMonth;

				anualSalary = (monthlySalary * totalMonth)
						+ ((monthlySalary / localJoinDate.lengthOfMonth()) * totalDays);

			} else {
				totalMonth = 16 - joinMonth;
				anualSalary = monthlySalary * totalMonth;
			}
		} else {// joined before previous financial year
			totalMonth = 12;
			anualSalary = monthlySalary * totalMonth;
		}

		// calculate income tax
		if (anualSalary <= 250000) {
			toatalIncomeTax = 0;
		} else if (anualSalary > 250000 && anualSalary <= 500000) {
			anualSalary = anualSalary - 250000;
			toatalIncomeTax = anualSalary * 5 / 100;
		} else if (anualSalary > 500000 && anualSalary <= 1000000) {
			toatalIncomeTax = 250000 * 5 / 100;
			long taxableRemainingSalary = anualSalary - 500000;
			toatalIncomeTax = +taxableRemainingSalary * 10 / 100;
		} else if (anualSalary > 1000000) {
			toatalIncomeTax = 250000 * 5 / 100;
			toatalIncomeTax = +500000 * 10 / 100;
			long taxableRemainingSalary = anualSalary - 1000000;
			toatalIncomeTax = +taxableRemainingSalary * 20 / 100;
		}

		long cessTax = 0;

		if (anualSalary > 2500000) {
			cessTax = toatalIncomeTax * 2 / 100;
		}

		System.out.println(
				"anualSalary:" + anualSalary + "  total income tax:" + toatalIncomeTax + " cess Tax:" + cessTax);

		empDetail.setTaxAmount(Long.toString(toatalIncomeTax));
		empDetail.setCessTax(Long.toString(cessTax));
		empDetail.setAnualSalary(Long.toString(anualSalary));

		return empDetail;
	}
}
