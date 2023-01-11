package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class EmailAddressNotFoundException extends CustomServiceException {

    public EmailAddressNotFoundException(String message, ExceptionCode code) {
        super(message, code);
    }
}