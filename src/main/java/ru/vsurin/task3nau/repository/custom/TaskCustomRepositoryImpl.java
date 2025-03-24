package ru.vsurin.task3nau.repository.custom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import ru.vsurin.task3nau.domain.Project;
import ru.vsurin.task3nau.domain.Task;

import java.util.List;

/**
 * Реализация кастомного репозитория задач
 */
@Repository
public class TaskCustomRepositoryImpl implements TaskCustomRepository {

    private final EntityManager entityManager;

    public TaskCustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Task> findByProjectAndTitleContaining(String titleFragment, Project project) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> query = cb.createQuery(Task.class);
        Root<Task> task = query.from(Task.class);

        Predicate titlePredicate = cb.like(cb.lower(task.get("title")), "%" + titleFragment.toLowerCase() + "%");
        Predicate projectPredicate = cb.equal(task.get("project"), project);

        query.select(task).where(cb.and(titlePredicate, projectPredicate));

        return entityManager.createQuery(query).getResultList();
    }
}
