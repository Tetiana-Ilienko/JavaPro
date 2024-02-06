package de.aittr.g_31_2_shop.exeption_handling;

import de.aittr.g_31_2_shop.exeption_handling.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.NotActiveException;

@ControllerAdvice
public class CommonAdvice {

    /**
     * Для логирования ошибок
     */
    private Logger logger = LoggerFactory.getLogger(CommonAdvice.class);


    @ExceptionHandler(ThirdTestException.class)
    // этот объект содержит в себе статус ответа и дополнительный объект, который мы хотим туда положить
    public ResponseEntity<Response> handleException(ThirdTestException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FourthTestException.class)
    public ResponseEntity<Response> handleException(FourthTestException e) {

        /** логирование ошибки без использования АОП*/
        // logger.error(String.format("Error: %s", e.getMessage()));
        // когда вызывается обработчик ошибок, пользователь будет проинформирован об ошибке
        // и в log ошибка будет занесена

        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductUpdateException.class)
    public ResponseEntity<Response> handleException(ProductUpdateException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductDeletionException.class)
    public ResponseEntity<Response> handleException(ProductDeletionException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotActiveException.class)
    public ResponseEntity<Response> handleException(NoActiveProductsException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
