package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class UsersNotAvailableException extends CustomServiceException {

    public UsersNotAvailableException(String message, ExceptionCode code) {
        super(message, code);
    }
}