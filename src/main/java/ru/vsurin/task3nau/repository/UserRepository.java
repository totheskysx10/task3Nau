package ru.vsurin.task3nau.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vsurin.task3nau.domain.User;

import java.util.Optional;

/**
 * Репозиторий пользователей
 */
@RepositoryRestResource(path = "users")
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Проверяет существование пользователя по имени
     * @param name имя
     */
    boolean existsByName(String name);

    /**
     * Ищет пользователя по имени
     * @param name имя
     */
    Optional<User> findByName(String name);
}
