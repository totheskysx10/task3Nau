package ru.vsurin.task3nau.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.vsurin.task3nau.domain.Comment;
import ru.vsurin.task3nau.domain.Task;

import java.util.List;

/**
 * Репозиторий комментариев
 */
public interface CommentRepository extends CrudRepository<Comment, Long> {

    /**
     * Ищет комментарии для конкретной задачи
     * @param task задача
     */
    @Query("SELECT c FROM Comment c WHERE c.task = :task")
    List<Comment> findCommentsByTask(Task task);
}
