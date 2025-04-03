package ru.vsurin.task3nau.repository.custom;

import ru.vsurin.task3nau.domain.Project;
import ru.vsurin.task3nau.domain.Task;

import java.util.List;

/**
 * Кастомный репозиторий задач
 */
public interface TaskCustomRepository {

    /**
     * Найти задачи по проекту и отрывку названия
     * @param titleFragment отрывок названия
     * @param project проект
     */
    List<Task> findByProjectAndTitleContaining(String titleFragment, Project project);
}
