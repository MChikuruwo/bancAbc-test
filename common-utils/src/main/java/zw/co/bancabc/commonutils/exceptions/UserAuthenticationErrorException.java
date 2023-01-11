package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class UserAuthenticationErrorException extends CustomServiceException {

    public UserAuthenticationErrorException(String message, ExceptionCode code) {
        super(message, code);
    }
}