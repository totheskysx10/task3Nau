package ru.vsurin.task3nau.response;

import java.time.LocalDate;

/**
 * DTO комментария
 */
public class CommentDto {
    private Long id;
    private String text;
    private LocalDate createdAt;
    private Long authorId;
    private Long taskId;

    public CommentDto() {
    }

    public CommentDto(Long id, String text, LocalDate createdAt, Long authorId, Long taskId) {
        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
        this.authorId = authorId;
        this.taskId = taskId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
