package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class UserNotOtpEnabledException extends CustomServiceException {

    public UserNotOtpEnabledException(String message, ExceptionCode code) {
        super(message, code);
    }
}