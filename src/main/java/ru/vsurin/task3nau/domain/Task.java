package ru.vsurin.task3nau.domain;

import jakarta.persistence.*;

import java.util.List;

/**
 * Задача
 */
@Entity
@Table(name = "tasks")
public class Task {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название
     */
    @Column
    private String title;

    /**
     * Статус
     */
    @Enumerated(EnumType.STRING)
    private Status status;

    /**
     * Приоритет
     */
    @Column
    private Integer priority;

    /**
     * Проект
     */
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    /**
     * Назначенный пользователь
     */
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User assignedUser;

    /**
     * Комментарии
     */
    @OneToMany(mappedBy = "task", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Comment> comments;

    /**
     * Вложения
     */
    @OneToMany(mappedBy = "task", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Attachment> attachments;

    public Task(String title, Status status, Integer priority, Project project, User assignedUser, List<Comment> comments, List<Attachment> attachments) {
        this.title = title;
        this.status = status;
        this.priority = priority;
        this.project = project;
        this.assignedUser = assignedUser;
        this.comments = comments;
        this.attachments = attachments;
    }

    public Task() {

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

    public Integer getPriority() {
        return priority;
    }

    public Project getProject() {
        return project;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                ", project=" + project +
                ", assignedUser=" + assignedUser +
                ", comments=" + comments +
                ", attachments=" + attachments +
                '}';
    }
}
