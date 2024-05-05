package kr.co.ipalab.lingobe.global.advice;

import static kr.co.ipalab.lingobe.global.advice.ErrorCode.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.ipalab.lingobe.global.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse<?>> passwordError(BadCredentialsException e) {

        ErrorResponse<?> body = ErrorResponse.builder()
            .status(BAD_REQUEST.value())
            .code(A01)
            .message(getErrorLog(e))
            .build();

        return ResponseEntity.status(BAD_REQUEST).body(body);
    }

    /**
     * 예외 메시지 핸들러
     *
     * @param e Exception
     * @return ErrorResponse
     */
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorResponse<?>> errorException(GlobalException e) {

        ErrorResponse<?> body = ErrorResponse.builder()
            .status(BAD_REQUEST.value())
            .code(e.code())
            .message(e.getMessage())
            .build();

        return ResponseEntity
            .status(BAD_REQUEST)
            .body(body);
    }

    /**
     * 필수 값 메시지 핸들러
     *
     * @param e Exception
     * @return ErrorResponse
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse<?>> requiredErrorException(MethodArgumentNotValidException e) {

        List<FieldError> errors = e.getBindingResult().getFieldErrors();

        Map<String, String> errorMessages = new HashMap<>();
        e.getBindingResult().getAllErrors()
            .forEach(c -> errorMessages.put(((FieldError) c).getField(), c.getDefaultMessage()));

        ErrorResponse<?> body = ErrorResponse.builder()
            .status(BAD_REQUEST.value())
            .code(P01)
            .message(errorMessages)
            .build();

        return ResponseEntity.status(BAD_REQUEST).body(body);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse<?>> accessDeniedException(AccessDeniedException e) {

        ErrorResponse<?> body = ErrorResponse.builder()
            .status(BAD_REQUEST.value())
            .code(A01)
            .message(A01.getDescription())
            .build();

        return ResponseEntity.status(BAD_REQUEST).body(body);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse<?>> noEndpoint(NoHandlerFoundException e) {
        ErrorResponse<?> body = ErrorResponse.builder()
            .status(BAD_REQUEST.value())
            .code(R01)
            .message(R01.getDescription())
            .build();
        return ResponseEntity.status(BAD_REQUEST).body(body);
    }



    private static String getErrorLog(Exception e) {
        String errorLog = e.getMessage().isEmpty() ? e.getCause().getMessage() : e.getMessage();
        log.error(errorLog);

        return errorLog;
    }
}
