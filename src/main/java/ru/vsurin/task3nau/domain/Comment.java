package ru.vsurin.task3nau.domain;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Комментарий
 */
@Entity
@Table(name = "comments")
public class Comment {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Текст комментария
     */
    @Column(columnDefinition = "text")
    private String text;

    /**
     * Дата создания
     */
    @Column
    private Date createdAt;

    /**
     * Автор
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    /**
     * Задача
     */
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    public Comment(String text, Date createdAt, User author, Task task) {
        this.text = text;
        this.createdAt = createdAt;
        this.author = author;
        this.task = task;
    }

    public Comment() {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                ", author=" + author +
                ", task=" + task +
                '}';
    }
}
