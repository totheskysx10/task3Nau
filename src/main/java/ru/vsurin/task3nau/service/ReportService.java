package ru.vsurin.task3nau.service;

import ru.vsurin.task3nau.domain.Report;
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
     * Получает отчёт по идентификатору
     * @param id идентификатор
     */
    Report getReportById(Long id) throws ReportNotFoundException;
}
