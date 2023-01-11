package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class ActivityNotFoundException extends CustomServiceException {

    public ActivityNotFoundException(String message, ExceptionCode code) {
        super(message, code);
    }
}
