package de.aittr.g_31_2_shop.exeption_handling;

public class Response {

    // создаем объект ответа и упаковываем в объект json ответ об ошибке
    private String message;
    // данный класс может сожержать больше полей (дата, возникновения ошибки, код статуса ошибки итп)

    public Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
