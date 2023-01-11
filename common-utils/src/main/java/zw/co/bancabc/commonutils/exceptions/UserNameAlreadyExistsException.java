package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class UserNameAlreadyExistsException extends CustomServiceException {

    public UserNameAlreadyExistsException(String message, ExceptionCode code) {
        super(message, code);
    }
}