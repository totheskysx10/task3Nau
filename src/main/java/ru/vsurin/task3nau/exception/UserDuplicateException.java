package ru.vsurin.task3nau.exception;

/**
 * Исключение при дублировании пользователей
 */
public class UserDuplicateException extends Exception {
    public UserDuplicateException(String message) {
        super(message);
    }
}
