package com.example.BS8_Personalizando_respuesta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@RestControllerAdvice   //Esta etiqueta es un conjunto de @ControllerAdvice (que trata las excepciones) y @RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException notFoundException, WebRequest webRequest){
        ExceptionResponse CustomError = new ExceptionResponse(new Date(), HttpStatus.NOT_ACCEPTABLE.value(), notFoundException.getMessage());
        return new ResponseEntity<ExceptionResponse>(CustomError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnprocesableException.class)
    public ResponseEntity<ExceptionResponse> handleUnprocesableException(UnprocesableException unprocesableException, WebRequest webRequest){
        ExceptionResponse CustomError = new ExceptionResponse(new Date(), 422, unprocesableException.getMessage());
        return new ResponseEntity<ExceptionResponse>(CustomError, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
