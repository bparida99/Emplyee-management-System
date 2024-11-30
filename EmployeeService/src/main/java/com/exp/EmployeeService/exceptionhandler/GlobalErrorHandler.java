package com.exp.EmployeeService.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handelRequestBodyError(MethodArgumentNotValidException ex){
      var error = ex.getBindingResult().
              getAllErrors().
              stream().
              map(DefaultMessageSourceResolvable::getDefaultMessage).
              sorted().
              collect(Collectors.joining(","));
      log.error(error);
      return ResponseEntity.
              status(HttpStatus.BAD_REQUEST).
              body(error);
    }
}
