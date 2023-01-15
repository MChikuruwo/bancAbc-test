package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class PaymentReferenceNotFoundException extends CustomServiceException {
    public PaymentReferenceNotFoundException(String message, ExceptionCode code) {
        super(message, code);
    }
}
