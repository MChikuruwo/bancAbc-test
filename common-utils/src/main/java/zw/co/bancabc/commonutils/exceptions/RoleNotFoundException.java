package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class RoleNotFoundException extends CustomServiceException {

    public RoleNotFoundException(String message, ExceptionCode code) {
        super(message, code);
    }
}