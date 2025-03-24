package ru.vsurin.task3nau.repository.custom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import ru.vsurin.task3nau.domain.Comment;
import ru.vsurin.task3nau.domain.Task;

import java.util.List;

/**
 * Реализация кастомного репозитория комментариев
 */
@Repository
public class CommentCustomRepositoryImpl implements CommentCustomRepository {

    private final EntityManager entityManager;

    public CommentCustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Comment> findCommentsByTaskAndTextFragment(String textFragment, Task task) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Comment> query = cb.createQuery(Comment.class);
        Root<Comment> comment = query.from(Comment.class);

        Predicate textPredicate = cb.like(cb.lower(comment.get("text")), "%" + textFragment.toLowerCase() + "%");
        Predicate taskPredicate = cb.equal(comment.get("task"), task);

        query.select(comment).where(cb.and(textPredicate, taskPredicate));

        return entityManager.createQuery(query).getResultList();
    }
}
