package zw.co.bancabc.commonutils.exceptions;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import zw.co.bancabc.commonutils.domain.enums.ExceptionCode;

@Getter
@RequiredArgsConstructor
public class CustomServiceException extends RuntimeException {

    protected final String message;

    protected final ExceptionCode code;
}
