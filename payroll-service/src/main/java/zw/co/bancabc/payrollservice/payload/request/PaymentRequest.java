package zw.co.bancabc.payrollservice.payload.request;

import zw.co.bancabc.payrollservice.business.model.Employee;

import java.math.BigInteger;

public record PaymentRequest(BigInteger salaryAmount, Employee employee) {

}
