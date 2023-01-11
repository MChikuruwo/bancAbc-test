package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class LoginEntriesUnavailableException extends CustomServiceException {

    public LoginEntriesUnavailableException(String message, ExceptionCode code) {
        super(message, code);
    }
}