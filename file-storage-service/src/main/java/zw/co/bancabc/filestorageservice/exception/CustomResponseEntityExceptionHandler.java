package zw.co.bancabc.filestorageservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import zw.co.bancabc.filestorageservice.business.dto.RestResponse;


@Slf4j
@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FileNotFoundException.class)
    public final ResponseEntity<RestResponse> handleRecordNotFoundException(final FileNotFoundException ex, final WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), HttpStatus.NOT_FOUND, "Failed");
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileStorageException.class)
    public final ResponseEntity<RestResponse> handleIllegalOperationException(final FileStorageException ex, final WebRequest request) {
        RestResponse errorDetails = new RestResponse(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE, "Failed");
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
    }

}