package ru.vsurin.task3nau.exception;

/**
 * Исключение, когда задача не найдена
 */
public class TaskNotFoundException extends Exception {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
