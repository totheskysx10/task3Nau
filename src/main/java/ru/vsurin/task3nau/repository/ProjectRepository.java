package ru.vsurin.task3nau.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vsurin.task3nau.domain.Project;

/**
 * Репозиторий проектов
 */
public interface ProjectRepository extends CrudRepository<Project, Long> {
}
