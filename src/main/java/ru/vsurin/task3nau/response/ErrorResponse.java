package ru.vsurin.task3nau.response;

/**
 * Ответ при возникновении ошибки
 */
public class ErrorResponse {

    /**
     * Сообщение с ошибкой
     */
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

