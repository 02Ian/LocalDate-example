package com.seveneleven.esl.advice;


import com.seveneleven.esl.exception.NoDataFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoDataFoundException.class)
    public final ResponseEntity<ErrorDetails> handleNoDataFoundExceptions(Exception ex, WebRequest request) {
        Map<String, Object> map = new LinkedHashMap<>();
        String message = null;
        if (ex instanceof NoDataFoundException) {
            NoDataFoundException exception = (NoDataFoundException) ex;
            message = exception.getMessage();
        }
        ErrorDetails errorDetails = new ErrorDetails(new Date(), message,
                request.getDescription(false),"1","Failed" );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false),"1","Failed");

        return super.handleExceptionInternal(ex, errorDetails, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false),"1","Failed");

        return super.handleExceptionInternal(ex, errorDetails, headers, HttpStatus.BAD_REQUEST, request);
    }
}