package ru.vsurin.task3nau.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import ru.vsurin.task3nau.domain.Report;
import ru.vsurin.task3nau.domain.ReportStatus;
import ru.vsurin.task3nau.domain.Task;
import ru.vsurin.task3nau.exception.ReportNotFoundException;
import ru.vsurin.task3nau.repository.ReportRepository;
import ru.vsurin.task3nau.repository.TaskRepository;
import ru.vsurin.task3nau.repository.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * Тесты сервиса генерации отчётов
 */
class ReportServiceTest {

    private ReportService reportService;

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TemplateEngine templateEngine;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reportService = new ReportServiceImpl(
                reportRepository,
                userRepository,
                taskRepository,
                templateEngine
        );
    }

    /**
     * Тест создания отчёта
     */
    @Test
    public void testCreateReport() {
        String expectedHtml = "reportCreate";
        Mockito.when(templateEngine.process(Mockito.eq("reportCreate"), Mockito.any(Context.class))).thenReturn(expectedHtml);

        Report reportToSave = new Report(ReportStatus.CREATED, expectedHtml);
        reportToSave.setId(1L);
        Mockito.when(reportRepository.save(Mockito.eq(reportToSave))).thenAnswer(invocation -> {
            Report report = invocation.getArgument(0);
            report.setId(1L);
            return report;
        });

        Long resultId = reportService.createReport();

        Assertions.assertEquals(1L, resultId);
        Mockito.verify(templateEngine).process(Mockito.eq("reportCreate"), Mockito.any(Context.class));
        Mockito.verify(reportRepository).save(reportToSave);
    }

    /**
     * Тест успешного получения отчёта по ID
     */
    @Test
    void getReportById() throws ReportNotFoundException {
        Long reportId = 1L;
        Report expectedReport = new Report(ReportStatus.FINISHED, "content");
        Mockito.when(reportRepository.findById(reportId)).thenReturn(Optional.of(expectedReport));

        Report actualReport = reportService.getReportById(reportId);
        ;
        Assertions.assertEquals(expectedReport, actualReport);
    }

    /**
     * Тест ошибки при получении несуществующего отчёта
     */
    @Test
    void getReportByIdNotFound() {
        Long reportId = 999L;
        Mockito.when(reportRepository.findById(reportId)).thenReturn(Optional.empty());

        Exception e = Assertions.assertThrows(ReportNotFoundException.class, () -> reportService.getReportById(reportId));
        Assertions.assertEquals(String.format("Отчёт с id %d не найден", reportId), e.getMessage());
    }

    /**
     * Тест генерации отчёта
     */
    @Test
    public void testGenerateReport() throws InterruptedException {
        String expectedHtml = "reportCreate";
        Mockito.when(templateEngine.process(Mockito.eq("reportCreate"), Mockito.any(Context.class))).thenReturn(expectedHtml);

        Report reportToSave = new Report(ReportStatus.CREATED, expectedHtml);
        reportToSave.setId(1L);
        Mockito.when(reportRepository.save(Mockito.eq(reportToSave))).thenAnswer(invocation -> {
            Report report = invocation.getArgument(0);
            report.setId(1L);
            return report;
        });
        Mockito.when(userRepository.count()).thenReturn(5L);
        Mockito.when(taskRepository.findAll()).thenReturn(List.of(new Task()));
        Mockito.when(templateEngine.process(Mockito.eq("reportTemplate"), Mockito.any(Context.class))).thenReturn("generatedContent");
        Mockito.when(reportRepository.findById(1L)).thenReturn(Optional.of(reportToSave));

        reportService.createReport();
        Thread.sleep(100);

        Assertions.assertEquals(ReportStatus.FINISHED, reportToSave.getStatus());
        Assertions.assertEquals("generatedContent", reportToSave.getContent());
        Mockito.verify(userRepository).count();
        Mockito.verify(taskRepository).findAll();
    }

    /**
     * Тест генерации отчёта с ошибкой
     */
    @Test
    public void testGenerateReportError() throws InterruptedException {
        String expectedHtml = "reportCreate";
        Mockito.when(templateEngine.process(Mockito.eq("reportCreate"), Mockito.any(Context.class))).thenReturn(expectedHtml);

        Report reportToSave = new Report(ReportStatus.CREATED, expectedHtml);
        reportToSave.setId(1L);
        Mockito.when(reportRepository.save(Mockito.eq(reportToSave))).thenAnswer(invocation -> {
            Report report = invocation.getArgument(0);
            report.setId(1L);
            return report;
        });
        Mockito.when(userRepository.count()).thenReturn(5L);
        Mockito.when(taskRepository.findAll()).thenThrow(new RuntimeException());
        Mockito.when(templateEngine.process(Mockito.eq("reportError"), Mockito.any(Context.class))).thenReturn("errorContent");
        Mockito.when(reportRepository.findById(1L)).thenReturn(Optional.of(reportToSave));

        reportService.createReport();
        Thread.sleep(100);

        Assertions.assertEquals(ReportStatus.ERROR, reportToSave.getStatus());
        Assertions.assertEquals("errorContent", reportToSave.getContent());
        Mockito.verify(userRepository).count();
        Mockito.verify(taskRepository).findAll();
    }
}