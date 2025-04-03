package ru.vsurin.task3nau.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Проект
 */
@Entity
@Table(name = "projects")
public class Project {

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
    private String name;

    /**
     * Описание
     */
    @Column
    private String description;

    /**
     * Дата создания
     */
    @Column
    private LocalDate createdAt;

    /**
     * Владелец проекта
     */
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    /**
     * Задачи в проекте
     */
    @OneToMany(mappedBy = "project", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Task> tasks;

    public Project(String name, String description, LocalDate createdAt, User owner, List<Task> tasks) {
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.owner = owner;
        this.tasks = tasks;
    }

    public Project() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", owner=" + owner +
                ", tasks=" + tasks +
                '}';
    }
}
