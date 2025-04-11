package ru.vsurin.task3nau.service;

import ru.vsurin.task3nau.domain.User;
import ru.vsurin.task3nau.exception.UserDuplicateException;
import ru.vsurin.task3nau.exception.UserNotFoundException;

/**
 * Интерфейс бизнес-логики для управления пользователями
 */
public interface UserService {

    /**
     * Создает пользователя
     * @param user новый пользователь
     */
    void createUser(User user) throws UserDuplicateException;

    /**
     * Получает пользователя по имени
     * @param name имя
     */
    User getUserByName(String name) throws UserNotFoundException;

    /**
     * Удаляет пользователя по имени
     * @param name имя
     */
    void deleteUser(String name) throws UserNotFoundException;
}
