package recruitment.task.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppRuntimeException.class)
    public ResponseEntity<ResponseErrorDTO> handleAppRuntimeException(final AppRuntimeException ex) {


        return new ResponseEntity<>(
                new ResponseErrorDTO(
                        ex.getStatusCode(),
                        ex.getMessage()),
                HttpStatus.valueOf(ex.getStatusCode()));
    }
}
