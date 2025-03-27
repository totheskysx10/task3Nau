package ru.vsurin.task3nau.domain;

import jakarta.persistence.*;

/**
 * Вложение
 */
@Entity
@Table(name = "attachments")
public class Attachment {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название файла
     */
    @Column
    private String fileName;

    /**
     * Ссылка на файл
     */
    @Column
    private String fileUrl;

    /**
     * Задача, куда прикреплён файл
     */
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    public Attachment(String fileName, String fileUrl, Task task) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.task = task;
    }

    public Attachment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", task=" + task +
                '}';
    }
}
