package ru.vsurin.task3nau.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsurin.task3nau.domain.Report;

/**
 * Репозиторий отчётов
 */
public interface ReportRepository extends JpaRepository<Report, Long> {
}
