package ru.vsurin.task3nau.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsurin.task3nau.domain.Report;
import ru.vsurin.task3nau.exception.ReportNotFoundException;
import ru.vsurin.task3nau.response.ReportDto;
import ru.vsurin.task3nau.service.ReportService;

/**
 * Контроллер для отчётов
 */
@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * Создаёт пустой отчёт и запускает его генерацию
     */
    @PostMapping("/create")
    public ResponseEntity<Long> createReport() {
        Long reportId = reportService.createReport();
        return new ResponseEntity<>(reportId, HttpStatus.CREATED);
    }

    /**
     * Получает отчёт по идентификатору
     * @param id идентификатор
     */
    @GetMapping("/get")
    public ResponseEntity<ReportDto> getReportById(@RequestParam Long id) throws ReportNotFoundException {
        Report report = reportService.getReportById(id);
        ReportDto reportDto = new ReportDto(report.getId(), report.getStatus().toString(), report.getContent());
        return ResponseEntity.ok(reportDto);
    }
}
