package ru.vsurin.task3nau.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vsurin.task3nau.exception.ReportNotFoundException;
import ru.vsurin.task3nau.service.ReportService;

/**
 * Контроллер для отображения отчёта
 */
@Controller
@RequestMapping("/reports/view")
public class ReportControllerView {

    private final ReportService reportService;

    public ReportControllerView(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * Получает отчёт (содержимое) по идентификатору
     * @param id идентификатор
     * @param model модель
     */
    @GetMapping
    public String getReport(@RequestParam Long id, Model model) throws ReportNotFoundException {
        String reportContent = reportService.getReportContentById(id);
        model.addAttribute("reportContent", reportContent);
        return "reportView";
    }
}
