package ru.vsurin.task3nau.exception;

/**
 * Исключение при дублировании задач
 */
public class TaskDuplicateException extends Exception {
    public TaskDuplicateException(String message) {
        super(message);
    }
}
