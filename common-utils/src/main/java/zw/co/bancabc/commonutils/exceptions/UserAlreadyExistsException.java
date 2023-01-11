package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class UserAlreadyExistsException extends CustomServiceException {

    public UserAlreadyExistsException(String message, ExceptionCode code) {
        super(message, code);
    }
}
