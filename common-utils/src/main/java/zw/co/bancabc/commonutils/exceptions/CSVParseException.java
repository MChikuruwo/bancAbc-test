package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class CSVParseException extends CustomServiceException {
    public CSVParseException(String message, ExceptionCode code) {
        super(message, code);
    }
}
