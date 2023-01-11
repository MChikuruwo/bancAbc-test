package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class userIdentityNumberNotFoundException extends CustomServiceException {

    public userIdentityNumberNotFoundException(String message, ExceptionCode code) {
        super(message, code);
    }
}