package ru.vsurin.task3nau.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vsurin.task3nau.domain.Comment;
import ru.vsurin.task3nau.domain.Task;
import ru.vsurin.task3nau.domain.User;

import java.util.List;

/**
 * Репозиторий комментариев
 */
@RepositoryRestResource(path = "comments")
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Ищет комментарии для конкретной задачи
     * @param task задача
     */
    @Query("SELECT c FROM Comment c WHERE c.task = :task")
    List<Comment> findCommentsByTask(Task task);

    /**
     * Ищет комментарии пользователя
     * @param user пользователь
     */
    @Query("SELECT c FROM Comment c WHERE c.author = :user")
    List<Comment> findCommentsByUser(User user);
}
