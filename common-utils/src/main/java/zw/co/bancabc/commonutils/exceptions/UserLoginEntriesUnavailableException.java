package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class UserLoginEntriesUnavailableException extends CustomServiceException {

    public UserLoginEntriesUnavailableException(String message, ExceptionCode code) {
        super(message, code);
    }
}