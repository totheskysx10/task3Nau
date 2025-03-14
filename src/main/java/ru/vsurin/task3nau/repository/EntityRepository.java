package ru.vsurin.task3nau.repository;

import java.util.List;

/**
 * Базовый интерфейс доступа к данным
 * @param <T>  тип сущности
 * @param <ID> тип идентификатора сущности
 */
public interface EntityRepository<T, ID> {

    /**
     * Создать объект
     * @param entity объект
     */
    void create(T entity);

    /**
     * Получить объект
     * @param id идентификатор
     */
    T read(ID id);

    /**
     * Обновить объект
     * @param entity объект
     */
    void update(T entity);

    /**
     * Удалить объект
     * @param id идентификатор
     */
    void delete(ID id);

    /**
     * Найти все объекты
     */
    List<T> findAll();

    /**
     * Проверяет существование объекта по идентификатору
     * @param id идентификатор
     * @return true, если объект найден
     */
    boolean existsById(ID id);
}

