package ru.vsurin.task3nau.response;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO пользователя (без пароля)
 */
public class UserDto {
    private Long id;
    private String name;
    private List<String> roles;
    private LocalDate creationDate;
    private List<Long> taskIds;
    private List<Long> projectIds;
    private List<Long> commentIds;

    public UserDto() {
    }

    public UserDto(Long id, String name, List<String> roles, LocalDate creationDate,
                   List<Long> taskIds, List<Long> projectIds, List<Long> commentIds) {
        this.id = id;
        this.name = name;
        this.roles = roles;
        this.creationDate = creationDate;
        this.taskIds = taskIds;
        this.projectIds = projectIds;
        this.commentIds = commentIds;
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<Long> getTaskIds() {
        return taskIds;
    }

    public void setTaskIds(List<Long> taskIds) {
        this.taskIds = taskIds;
    }

    public List<Long> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(List<Long> projectIds) {
        this.projectIds = projectIds;
    }

    public List<Long> getCommentIds() {
        return commentIds;
    }

    public void setCommentIds(List<Long> commentIds) {
        this.commentIds = commentIds;
    }
}
