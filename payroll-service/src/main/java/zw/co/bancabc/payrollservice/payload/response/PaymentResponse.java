package zw.co.bancabc.payrollservice.payload.response;

import zw.co.bancabc.payrollservice.business.model.Employee;

import java.math.BigInteger;

public record PaymentResponse(BigInteger salaryAmount, Employee employee)  {
}
