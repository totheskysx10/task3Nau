package ru.vsurin.task3nau.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.vsurin.task3nau.domain.Attachment;
import ru.vsurin.task3nau.domain.Task;

import java.util.List;

/**
 * Репозиторий вложений
 */
public interface AttachmentRepository extends CrudRepository<Attachment, Long> {

    /**
     * Ищет вложения для конкретной задачи
     * @param task задача
     */
    @Query("SELECT a FROM Attachment a WHERE a.task = :task")
    List<Attachment> findAttachmentsByTask(Task task);
}
