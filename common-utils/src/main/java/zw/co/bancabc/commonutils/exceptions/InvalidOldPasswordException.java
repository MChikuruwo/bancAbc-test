package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class InvalidOldPasswordException extends CustomServiceException {
    public InvalidOldPasswordException(String message, ExceptionCode code) {
        super(message, code);
    }
}