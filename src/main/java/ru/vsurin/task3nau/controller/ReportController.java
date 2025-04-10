package ru.vsurin.task3nau.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsurin.task3nau.service.ReportService;

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
}
