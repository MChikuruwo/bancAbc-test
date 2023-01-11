package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class InvalidIdNumberException extends CustomServiceException {

    public InvalidIdNumberException(String message, ExceptionCode code) {
        super(message, code);
    }
}