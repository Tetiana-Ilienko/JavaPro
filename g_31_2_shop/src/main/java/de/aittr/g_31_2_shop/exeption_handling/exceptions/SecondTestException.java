package de.aittr.g_31_2_shop.exeption_handling.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class SecondTestException extends RuntimeException{
    public SecondTestException(String message) {
        super(message);
    }
}
