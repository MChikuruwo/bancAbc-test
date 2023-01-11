package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class UnexpectedErrorException extends CustomServiceException {
    public UnexpectedErrorException(String message, ExceptionCode code) {
        super(message, code);
    }
}