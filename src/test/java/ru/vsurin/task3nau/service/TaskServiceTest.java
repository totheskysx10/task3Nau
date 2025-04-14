package ru.vsurin.task3nau.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import ru.vsurin.task3nau.domain.*;
import ru.vsurin.task3nau.exception.TaskNotFoundException;
import ru.vsurin.task3nau.repository.CommentRepository;
import ru.vsurin.task3nau.repository.TaskRepository;
import ru.vsurin.task3nau.repository.custom.TaskCustomRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskCustomRepository taskCustomRepository;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PlatformTransactionManager transactionManager;

    @Mock
    private TransactionStatus transactionStatus;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskService = new TaskServiceImpl(taskRepository, commentRepository, transactionManager, taskCustomRepository);
    }

    /**
     * Тест создания задачи
     */
    @Test
    void createTask() {
        Task task1 = new Task();
        taskService.createTask(task1);
        verify(taskRepository).save(task1);
    }

    /**
     * Тест получения задачи по id
     */
    @Test
    void getTaskById() throws TaskNotFoundException {
        Task task1 = new Task();
        task1.setTitle("task1");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));

        Task foundTask = taskService.getTaskById(1L);
        assertNotNull(foundTask);
        assertEquals("task1", foundTask.getTitle());
    }

    /**
     * Тест удаления задачи
     */
    @Test
    void deleteTaskWithComments() throws TaskNotFoundException {
        Task task = new Task();
        task.setId(1L);

        Comment comment1 = new Comment();
        comment1.setText("comment1");
        Comment comment2 = new Comment();
        comment2.setText("comment2");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(commentRepository.findCommentsByTask(task)).thenReturn(List.of(comment1, comment2));
        when(transactionManager.getTransaction(any())).thenReturn(transactionStatus);

        taskService.deleteTaskWithComments(1L);

        verify(taskRepository).delete(task);
        verify(commentRepository).deleteAll(List.of(comment1, comment2));
    }

    /**
     * Тест ошибки удаления задачи
     */
    @Test
    void deleteTaskWithCommentsFail() throws TaskNotFoundException {
        Task task = new Task();
        task.setId(1L);

        Comment comment1 = new Comment();
        comment1.setText("comment1");
        Comment comment2 = new Comment();
        comment2.setText("comment2");

        task.setComments(List.of(comment1, comment2));

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(commentRepository.findCommentsByTask(task)).thenReturn(List.of(comment1, comment2));
        when(transactionManager.getTransaction(any())).thenReturn(transactionStatus);
        doThrow(new DataAccessException("Error") {}).when(commentRepository).deleteAll(anyList());

        try {
            taskService.deleteTaskWithComments(1L);
        } catch (DataAccessException e) {
            verify(transactionManager, times(1)).rollback(transactionStatus);
            verify(commentRepository, times(1)).deleteAll(List.of(comment1, comment2));
            verify(taskRepository, times(0)).delete(task);
            assertEquals(2, task.getComments().size());
        }
    }
}