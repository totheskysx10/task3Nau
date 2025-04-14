package ru.vsurin.task3nau.domain;

import jakarta.persistence.*;

/**
 * Отчёт
 */
@Entity
@Table(name = "reports")
public class Report {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Статус
     */
    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    /**
     * Содержимое
     */
    @Column(columnDefinition = "TEXT")
    private String content;

    public Report(ReportStatus status, String content) {
        this.status = status;
        this.content = content;
    }

    public Report() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
