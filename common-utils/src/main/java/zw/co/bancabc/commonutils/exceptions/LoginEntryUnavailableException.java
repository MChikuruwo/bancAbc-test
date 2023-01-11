package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class LoginEntryUnavailableException extends CustomServiceException {

    public LoginEntryUnavailableException(String message, ExceptionCode code) {
        super(message, code);
    }
}