package com.barber.booking.utility;

import com.barber.booking.exceptions.HaircutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionControllerAdvice {

    @Autowired
    private Environment environment;

    @ExceptionHandler(HaircutException.class)
    public ResponseEntity<ErrorInfo> haircutExceptionHandler(HaircutException exception) {

        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorMessage(exception.getMessage());
        errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
}
