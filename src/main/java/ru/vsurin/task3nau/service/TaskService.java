package ru.vsurin.task3nau.service;

import ru.vsurin.task3nau.domain.Project;
import ru.vsurin.task3nau.domain.Task;
import ru.vsurin.task3nau.exception.TaskNotFoundException;

import java.util.List;

/**
 * Интерфейс бизнес-логики для управления задачами
 */
public interface TaskService {

    /**
     * Создает задачу
     * @param task новая задача
     */
    void createTask(Task task);

    /**
     * Получает задачу по идентификатору
     * @param id идентификатор
     */
    Task getTaskById(Long id) throws TaskNotFoundException;

    /**
     * Удаляет задачу и все связанные комментарии
     * @param id идентификатор
     */
    void deleteTaskWithComments(Long id) throws TaskNotFoundException;

    /**
     * Найти задачи по проекту и отрывку названия
     * @param titleFragment отрывок названия
     * @param project проект
     */
    List<Task> findByProjectAndTitleContaining(String titleFragment, Project project);

    /**
     * Находит все задачи
     */
    Iterable<Task> findAll();
}
