package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class AuditTrailNotFoundException extends CustomServiceException {

    public AuditTrailNotFoundException(String message, ExceptionCode code) {
        super(message, code);
    }
}