package ru.vsurin.task3nau.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vsurin.task3nau.domain.User;

/**
 * Репозиторий пользователей
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
