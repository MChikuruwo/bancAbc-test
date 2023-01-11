package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class MobileNumberNotFoundException extends CustomServiceException {

    public MobileNumberNotFoundException(String message, ExceptionCode code) {
        super(message, code);
    }
}