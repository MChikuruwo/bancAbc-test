package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class PaymentsUnavailableException extends CustomServiceException {
    public PaymentsUnavailableException(String message, ExceptionCode code) {
        super(message, code);
    }
}
