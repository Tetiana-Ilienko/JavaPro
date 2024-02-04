package de.aittr.g_31_2_shop.exeption_handling.exceptions;

public class FirstTestException extends RuntimeException {
    //  название исключения должно говорить о причинах этого исключения (здесь используется учебное название)
    //  наследуем от более подходящего нам Exception (соответственно оно может быть  проверяемым / не проверяемым)

    // генерируется конструктор, который будет содержать информационное сообщение
    public FirstTestException(String message) {
        super(message);
    }
}
