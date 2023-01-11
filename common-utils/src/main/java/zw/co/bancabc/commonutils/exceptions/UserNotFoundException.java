package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class UserNotFoundException extends CustomServiceException {

    public UserNotFoundException(String message, ExceptionCode code) {
        super(message, code);
    }
}