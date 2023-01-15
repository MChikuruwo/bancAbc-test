package zw.co.bancabc.commonutils.exceptions;

import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

public class CSVUploadException extends CustomServiceException {
    public CSVUploadException(String message, ExceptionCode code) {
        super(message, code);
    }
}
