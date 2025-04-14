package ru.vsurin.task3nau.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
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
     * Шифрованный пароль
     */
    @Column
    private String password;

    /**
     * Роли пользователя
     */
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

    /**
     * Дата создания
     */
    @Column
    private LocalDate creationDate;

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

    public User() {

    }

    public User(Long id, String name, String password, List<Role> roles, LocalDate creationDate, List<Task> tasks, List<Project> ownedProjects, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.creationDate = creationDate;
        this.tasks = tasks;
        this.ownedProjects = ownedProjects;
        this.comments = comments;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", creationDate=" + creationDate +
                ", tasks=" + tasks +
                ", ownedProjects=" + ownedProjects +
                ", comments=" + comments +
                '}';
    }
}