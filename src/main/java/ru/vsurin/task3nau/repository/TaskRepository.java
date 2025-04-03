package ru.vsurin.task3nau.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vsurin.task3nau.domain.Status;
import ru.vsurin.task3nau.domain.Task;
import ru.vsurin.task3nau.domain.User;

import java.util.List;

/**
 * Репозиторий задач
 */
@RepositoryRestResource(path = "tasks")
public interface TaskRepository extends CrudRepository<Task, Long> {

    /**
     * Найти задачи по статусу и приоритету
     * @param status статус
     * @param priority приоритет
     */
    List<Task> findByStatusAndPriority(Status status, int priority);

    /**
     * Ищет задачи пользователя
     * @param user пользователь
     */
    @Query("SELECT t FROM Task t WHERE t.assignedUser = :user")
    List<Task> findTasksByUser(User user);
}
