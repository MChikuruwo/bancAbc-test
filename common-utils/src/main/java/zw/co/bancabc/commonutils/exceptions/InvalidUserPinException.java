package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class InvalidUserPinException extends CustomServiceException {

    public InvalidUserPinException(String message, ExceptionCode code) {
        super(message, code);
    }
}