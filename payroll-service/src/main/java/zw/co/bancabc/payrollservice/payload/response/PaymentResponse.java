package zw.co.bancabc.payrollservice.payload.response;

import zw.co.bancabc.commonutils.domain.enums.PaymentStatus;

import java.math.BigInteger;

public record PaymentResponse(String employeeName, String employeeCode, BigInteger salaryAmount,
                              PaymentStatus paymentStatus) {

}
