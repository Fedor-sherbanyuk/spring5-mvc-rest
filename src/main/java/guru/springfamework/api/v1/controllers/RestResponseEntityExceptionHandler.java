package guru.springfamework.api.v1.controllers;

import guru.springfamework.api.v1.services.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handlerNotFoundException(Exception e, WebRequest webRequest){

        return new ResponseEntity<Object>("Resource not Found",new HttpHeaders(), HttpStatus.NOT_FOUND);

    }

}
