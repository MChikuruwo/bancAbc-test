package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class InvalidUsernameException extends CustomServiceException {

    public InvalidUsernameException(String message, ExceptionCode code) {
        super(message, code);
    }
}