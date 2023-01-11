package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class InvalidInputException extends CustomServiceException {

    public InvalidInputException(String message, ExceptionCode code) {
        super(message, code);
    }
}