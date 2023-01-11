package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class UserDeviceDetailsNotFoundException extends CustomServiceException {

    public UserDeviceDetailsNotFoundException(String message, ExceptionCode code) {
        super(message, code);
    }
}