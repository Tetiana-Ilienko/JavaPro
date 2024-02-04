package de.aittr.g_31_2_shop.exeption_handling;

import de.aittr.g_31_2_shop.exeption_handling.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.NotActiveException;

@ControllerAdvice
public class CommonAdvice {
    @ExceptionHandler(ThirdTestException.class)
    // этот объект содержит в себе статус ответа и дополнительный объект, который мы хотим туда положить
    public ResponseEntity<Response> handleException(ThirdTestException e){
        Response response = new Response(e.getMessage());
        return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FourthTestException.class)
    public ResponseEntity<Response> handleException(FourthTestException e){
        Response response = new Response(e.getMessage());
        return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductUpdateException.class)
    public ResponseEntity<Response> handleException(ProductUpdateException e){
        Response response = new Response(e.getMessage());
        return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductDeletionException.class)
    public ResponseEntity<Response> handleException(ProductDeletionException e){
        Response response = new Response(e.getMessage());
        return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotActiveException.class)
    public ResponseEntity<Response> handleException(NoActiveProductsException e){
        Response response = new Response(e.getMessage());
        return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
