package ru.vsurin.task3nau.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vsurin.task3nau.domain.User;

/**
 * Репозиторий пользователей
 */
@RepositoryRestResource(path = "users")
public interface UserRepository extends CrudRepository<User, Long> {
}
