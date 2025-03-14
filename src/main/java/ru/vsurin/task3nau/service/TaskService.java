package ru.vsurin.task3nau.service;

import ru.vsurin.task3nau.domain.Status;
import ru.vsurin.task3nau.domain.Task;
import ru.vsurin.task3nau.exception.TaskDuplicateException;
import ru.vsurin.task3nau.exception.TaskNotFoundException;

import java.util.List;

/**
 * Интерфейс бизнес-логики для управления задачами
 */
public interface TaskService {

    /**
     * Создать задачу
     * @param id идентификатор
     * @param title название
     * @param status статус
     */
    void createTask(Long id, String title, Status status) throws TaskDuplicateException;

    /**
     * Найти задачу по идентификатору
     * @param id идентификатор
     */
    Task findById(Long id) throws TaskNotFoundException;

    /**
     * Обновить статус задачи
     * @param id идентификатор
     * @param status статус
     */
    void updateTaskStatus(Long id, Status status) throws TaskNotFoundException;

    /**
     * Удалить задачу
     * @param id идентификатор
     */
    void deleteById(Long id);

    /**
     * Найти все задачи
     */
    List<Task> findAll();
}
