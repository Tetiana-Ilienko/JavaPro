package de.aittr.g_31_2_shop.exeption_handling.exceptions;

public class NoActiveProductsException extends RuntimeException{
    public NoActiveProductsException(String message) {
        super(message);
    }
}
