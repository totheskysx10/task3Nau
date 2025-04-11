package ru.vsurin.task3nau.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import ru.vsurin.task3nau.domain.FutureResult;
import ru.vsurin.task3nau.domain.Report;
import ru.vsurin.task3nau.domain.ReportStatus;
import ru.vsurin.task3nau.domain.Task;
import ru.vsurin.task3nau.exception.ReportNotFoundException;
import ru.vsurin.task3nau.repository.ReportRepository;
import ru.vsurin.task3nau.repository.TaskRepository;
import ru.vsurin.task3nau.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TemplateEngine templateEngine;

    public ReportServiceImpl(ReportRepository reportRepository,
                             UserRepository userRepository,
                             TaskRepository taskRepository,
                             TemplateEngine templateEngine) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.templateEngine = templateEngine;
    }

    @Override
    public Long createReport() {
        Context context = new Context();
        String htmlContent = templateEngine.process("reportCreate", context);
        Report report = new Report(ReportStatus.CREATED, htmlContent);
        reportRepository.save(report);
        Long id = report.getId();
        generateReport(id);
        return id;
    }

    @Override
    public Report getReportById(Long id) throws ReportNotFoundException {
        Optional<Report> optionalReport = reportRepository.findById(id);
        Report report = optionalReport.orElseThrow(() -> new ReportNotFoundException(String.format("Отчёт с id %d не найден", id)));

        return report;
    }

    /**
     * Генерирует отчёт асинхронно
     * @param id идентификатор
     */
    private CompletableFuture<Void> generateReport(Long id) {
        return CompletableFuture.runAsync(() -> {
            Optional<Report> optionalReport = reportRepository.findById(id);

            if (optionalReport.isEmpty()) {
                return;
            }

            Report report = optionalReport.get();

            try {
                Long startTime = System.currentTimeMillis();

                CompletableFuture<FutureResult<Long>> userCountFuture = CompletableFuture.supplyAsync(() -> {
                    Long taskStart = System.currentTimeMillis();
                    return new FutureResult<>(userRepository.count(), System.currentTimeMillis() - taskStart);
                });

                CompletableFuture<FutureResult<List<Task>>> taskListFuture = CompletableFuture.supplyAsync(() -> {
                    Long taskStart = System.currentTimeMillis();
                    return new FutureResult<>(taskRepository.findAll(), System.currentTimeMillis() - taskStart);
                });

                FutureResult<Long> userCountResult = userCountFuture.join();
                FutureResult<List<Task>> taskListResult = taskListFuture.join();

                Long userCount = userCountResult.result();
                List<Task> taskList = taskListResult.result();

                Long userCountDuration = userCountResult.time();
                Long taskListDuration = taskListResult.time();

                Long totalDuration = System.currentTimeMillis() - startTime;

                Context context = new Context();
                context.setVariable("userCount", userCount);
                context.setVariable("taskList", taskList);
                context.setVariable("userCountDuration", userCountDuration);
                context.setVariable("taskListDuration", taskListDuration);
                context.setVariable("totalDuration", totalDuration);

                String htmlContent = templateEngine.process("reportTemplate", context);

                report.setContent(htmlContent);
                report.setStatus(ReportStatus.FINISHED);
                reportRepository.save(report);

            } catch (Exception e) {
                Context context = new Context();
                context.setVariable("reportError", e.getMessage());
                String htmlContent = templateEngine.process("reportError", context);
                report.setStatus(ReportStatus.ERROR);
                report.setContent(htmlContent);
                reportRepository.save(report);
            }
        });
    }
}
