package com.example.productservice.advices;


import com.example.productservice.dtos.ExceptionDto;
import com.example.productservice.exceptions.InvalidProductIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity<ExceptionDto> InvadlidProductIdexceptionHandler(Exception ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> exceptionHandler(Exception ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }
}
