package zw.co.bancabc.payrollservice.payload.request;

import zw.co.bancabc.commonutils.domain.enums.EmploymentContract;
import zw.co.bancabc.commonutils.domain.enums.Gender;
import zw.co.bancabc.commonutils.domain.enums.MaritalStatus;
import zw.co.bancabc.commonutils.domain.enums.Title;
import zw.co.bancabc.commonutils.domain.value.Email;
import zw.co.bancabc.commonutils.domain.value.MobileNumber;
import zw.co.bancabc.commonutils.domain.value.login.IdNumber;

import java.time.LocalDate;

public record EmployeeRequest(String firstName, String lastName, LocalDate dateOfBirth, Gender gender, Title title,
                              IdNumber idNumber, String physicalAddress, MaritalStatus maritalStatus,
                              MobileNumber mobileNumber, Email email, EmploymentContract contract, String employeeCode) {

}
