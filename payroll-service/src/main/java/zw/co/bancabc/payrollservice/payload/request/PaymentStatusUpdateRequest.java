package zw.co.bancabc.payrollservice.payload.request;

import zw.co.bancabc.commonutils.domain.enums.PaymentStatus;

public record PaymentStatusUpdateRequest(PaymentStatus paymentStatus) {
}
