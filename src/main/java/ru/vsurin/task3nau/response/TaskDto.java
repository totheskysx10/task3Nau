package ru.vsurin.task3nau.response;

import java.util.List;

/**
 * DTO задачи
 */
public class TaskDto {
    private Long id;
    private String title;
    private String status;
    private Integer priority;
    private Long projectId;
    private Long assignedUserId;
    private List<Long> commentIds;
    private List<Long> attachmentIds;

    public TaskDto() {
    }

    public TaskDto(Long id, String title, String status, Integer priority, Long projectId,
                   Long assignedUserId, List<Long> commentIds, List<Long> attachmentIds) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.priority = priority;
        this.projectId = projectId;
        this.assignedUserId = assignedUserId;
        this.commentIds = commentIds;
        this.attachmentIds = attachmentIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getAssignedUserId() {
        return assignedUserId;
    }

    public void setAssignedUserId(Long assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    public List<Long> getCommentIds() {
        return commentIds;
    }

    public void setCommentIds(List<Long> commentIds) {
        this.commentIds = commentIds;
    }

    public List<Long> getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(List<Long> attachmentIds) {
        this.attachmentIds = attachmentIds;
    }
}
