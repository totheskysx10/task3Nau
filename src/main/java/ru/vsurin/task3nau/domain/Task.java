package ru.vsurin.task3nau.domain;

/**
 * Задача
 */
public class Task {

    /**
     * Идентификатор
     */
    private Long id;

    /**
     * Название
     */
    private String title;

    /**
     * Статус
     */
    private Status status;

    public Task(String title, Status status) {
        this.title = title;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", status=" + status +
                '}';
    }
}
