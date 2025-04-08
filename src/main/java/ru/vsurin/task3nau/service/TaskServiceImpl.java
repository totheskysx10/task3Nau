package ru.vsurin.task3nau.service;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.vsurin.task3nau.domain.Comment;
import ru.vsurin.task3nau.domain.Project;
import ru.vsurin.task3nau.domain.Task;
import ru.vsurin.task3nau.exception.TaskNotFoundException;
import ru.vsurin.task3nau.repository.CommentRepository;
import ru.vsurin.task3nau.repository.TaskRepository;
import ru.vsurin.task3nau.repository.custom.TaskCustomRepository;

import java.util.List;
import java.util.Optional;

/**
 * Реализация сервиса задач
 */
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;
    private final PlatformTransactionManager transactionManager;
    private final TaskCustomRepository taskCustomRepository;;

    public TaskServiceImpl(TaskRepository taskRepository,
                           CommentRepository commentRepository,
                           PlatformTransactionManager transactionManager,
                           TaskCustomRepository taskCustomRepository) {
        this.taskRepository = taskRepository;
        this.commentRepository = commentRepository;
        this.transactionManager = transactionManager;
        this.taskCustomRepository = taskCustomRepository;
    }

    @Override
    public void createTask(Task task)  {
        taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) throws TaskNotFoundException {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElseThrow(() -> new TaskNotFoundException("Задача с id " + id + " не найдена!"));
    }

    @Override
    public void deleteTaskWithComments(Long id) throws TaskNotFoundException {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            Task task = getTaskById(id);
            List<Comment> comments = commentRepository.findCommentsByTask(task);
            commentRepository.deleteAll(comments);
            taskRepository.delete(task);
            transactionManager.commit(status);
        } catch (DataAccessException e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    @Override
    public List<Task> findByProjectAndTitleContaining(String titleFragment, Project project) {
        return taskCustomRepository.findByProjectAndTitleContaining(titleFragment, project);
    }

    @Override
    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }
}
