package ru.vsurin.task3nau.domain;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * Пользователь
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Имя
     */
    @Column
    private String name;

    /**
     * Почта
     */
    @Column
    private String email;

    /**
     * Дата создания
     */
    @Column
    private Date creationDate;

    /**
     * Задачи пользователя
     */
    @OneToMany(mappedBy = "assignedUser", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Task> tasks;

    /**
     * Проекты пользователя
     */
    @OneToMany(mappedBy = "owner", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Project> ownedProjects;

    /**
     * Комментарии пользователя
     */
    @OneToMany(mappedBy = "author", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Comment> comments;

    public User(String name, String email, Date creationDate, List<Task> tasks, List<Project> ownedProjects, List<Comment> comments) {
        this.name = name;
        this.email = email;
        this.creationDate = creationDate;
        this.tasks = tasks;
        this.ownedProjects = ownedProjects;
        this.comments = comments;
    }

    public User() {

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Project> getOwnedProjects() {
        return ownedProjects;
    }

    public void setOwnedProjects(List<Project> ownedProjects) {
        this.ownedProjects = ownedProjects;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", creationDate=" + creationDate +
                ", tasks=" + tasks +
                ", ownedProjects=" + ownedProjects +
                ", comments=" + comments +
                '}';
    }
}