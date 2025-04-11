package ru.vsurin.task3nau.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vsurin.task3nau.domain.Project;
import ru.vsurin.task3nau.domain.User;

import java.util.List;

/**
 * Репозиторий проектов
 */
@RepositoryRestResource(path = "projects")
public interface ProjectRepository extends CrudRepository<Project, Long> {

    /**
     * Ищет проекты пользователя
     * @param user пользователь
     */
    @Query("SELECT p FROM Project p WHERE p.owner = :user")
    List<Project> findProjectsByUser(User user);
}
