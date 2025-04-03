package ru.vsurin.task3nau.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vsurin.task3nau.domain.Project;

/**
 * Репозиторий проектов
 */
@RepositoryRestResource(path = "projects")
public interface ProjectRepository extends CrudRepository<Project, Long> {
}
