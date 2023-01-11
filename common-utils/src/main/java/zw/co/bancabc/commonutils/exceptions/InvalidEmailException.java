package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class InvalidEmailException extends CustomServiceException {

    public InvalidEmailException(final String message, final ExceptionCode code) {
        super(message, code);
    }
}