package ru.vsurin.task3nau.repository.custom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import ru.vsurin.task3nau.domain.Comment;

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
    public List<Comment> findCommentsByTextFragment(String textFragment) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Comment> query = cb.createQuery(Comment.class);
        Root<Comment> comment = query.from(Comment.class);

        Predicate textPredicate = cb.like(cb.lower(comment.get("text")), "%" + textFragment.toLowerCase() + "%");

        query.select(comment).where(textPredicate);

        return entityManager.createQuery(query).getResultList();
    }
}
