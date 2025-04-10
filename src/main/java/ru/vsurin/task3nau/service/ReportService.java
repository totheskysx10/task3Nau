package ru.vsurin.task3nau.service;

import ru.vsurin.task3nau.exception.ReportNotFoundException;

/**
 * Сервис отчётов
 */
public interface ReportService {

    /**
     * Создаёт пустой отчёт и запускает его генерацию
     */
    Long createReport();

    /**
     * Получает отчёт (содержимое) по идентификатору
     * @param id идентификатор
     */
    String getReportContentById(Long id) throws ReportNotFoundException;
}
