package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class MobileNumberInvalidException extends CustomServiceException {

    public MobileNumberInvalidException(String message, ExceptionCode code) {
        super(message, code);
    }
}
