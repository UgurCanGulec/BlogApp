package com.gulecugurcan.exception;

import com.gulecugurcan.util.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("status", status.value());
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        body.put("errors", errors);
        errors.forEach(log::error);
        return new ResponseEntity<>(new BaseResponse<>(null, ErrorType.BAD_REQUEST.name(), body, false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({PostInsertionFailException.class})
    public ResponseEntity<Object> handleAuthorNotFoundException(PostInsertionFailException ex, WebRequest webRequest) {
        Map<String ,Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("message", "Gönderi kaydedilme işlemi başarısız !");
        body.put("error message", ex.getMessage());
        log.error("Post could not inserted !");
        return new ResponseEntity<>(new BaseResponse<>(null, ErrorType.BAD_REQUEST.name(), body,false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({PostNotFoundException.class})
    public ResponseEntity<Object> handlePostNotFoundException(PostInsertionFailException ex, WebRequest webRequest) {
        Map<String ,Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("message", "Gönderi bulunamadı !");
        body.put("error message", ex.getMessage());
        log.error("Post could not found !");
        return new ResponseEntity<>(new BaseResponse<>(null, ErrorType.NOT_FOUND.name(), body,false), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(PostInsertionFailException ex, WebRequest webRequest) {
        Map<String ,Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("message", "Kullanıcı bulunamadı !");
        body.put("error message", ex.getMessage());
        log.error("User could not found !");
        return new ResponseEntity<>(new BaseResponse<>(null, ErrorType.NOT_FOUND.name(), body,false), HttpStatus.NOT_FOUND);
    }
}
