package ru.vsurin.task3nau.response;

/**
 * DTO отчёта
 */
public class ReportDto {

    public Long id;
    public String status;
    public String content;

    public ReportDto(Long id, String status, String content) {
        this.id = id;
        this.status = status;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
