package ru.vsurin.task3nau.exception;

/**
 * Исключение, когда пользователь не найден
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
