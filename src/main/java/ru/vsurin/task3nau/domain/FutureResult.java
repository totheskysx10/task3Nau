package ru.vsurin.task3nau.domain;

/**
 * Результат выполнения асинхронной задачи
 * @param result результат
 * @param time время выполнения
 * @param <T> тип результата
 */
public record FutureResult<T>(T result, Long time) {
}
