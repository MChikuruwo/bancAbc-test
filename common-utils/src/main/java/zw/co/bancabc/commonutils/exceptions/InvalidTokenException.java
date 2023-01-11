package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class InvalidTokenException extends CustomServiceException {
    public InvalidTokenException(String message, ExceptionCode code) {
        super(message, code);
    }
}