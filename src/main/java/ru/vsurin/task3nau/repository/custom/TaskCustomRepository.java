package ru.vsurin.task3nau.repository.custom;

import ru.vsurin.task3nau.domain.Task;

import java.util.List;

/**
 * Кастомный репозиторий задач
 */
public interface TaskCustomRepository {

    /**
     * Найти задачи по отрывку названия
     * @param titleFragment отрывок названия
     */
    List<Task> findByTitleContaining(String titleFragment);
}
