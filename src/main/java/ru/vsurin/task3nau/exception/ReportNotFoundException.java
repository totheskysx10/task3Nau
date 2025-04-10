package ru.vsurin.task3nau.exception;

/**
 * Исключение, когда отчёт не найден
 */
public class ReportNotFoundException extends Exception {
    public ReportNotFoundException(String message) {
        super(message);
    }
}
