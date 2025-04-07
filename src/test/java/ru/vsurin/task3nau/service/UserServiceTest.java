package ru.vsurin.task3nau.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import ru.vsurin.task3nau.domain.*;
import ru.vsurin.task3nau.exception.UserDuplicateException;
import ru.vsurin.task3nau.exception.UserNotFoundException;
import ru.vsurin.task3nau.repository.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Тест сервиса пользователей
 */
class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PlatformTransactionManager transactionManager;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private TransactionStatus transactionStatus;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(
                userRepository,
                transactionManager,
                taskRepository,
                commentRepository,
                projectRepository
        );
    }

    /**
     * Тест создания пользователя
     */
    @Test
    void createUser() throws UserDuplicateException {
        User user = new User();
        user.setName("testUser");
        when(userRepository.existsByName("testUser")).thenReturn(false);

        userService.createUser(user);

        verify(userRepository).save(user);
    }

    /**
     * Тест ошибки при создании дубликата пользователя
     */
    @Test
    void createUserDuplicate() {
        User user = new User();
        user.setName("existingUser");
        when(userRepository.existsByName("existingUser")).thenReturn(true);

        Exception e = assertThrows(UserDuplicateException.class, () -> userService.createUser(user));
        assertEquals("Пользователь уже существует!", e.getMessage());
        verify(userRepository, never()).save(user);
    }

    /**
     * Тест получения пользователя по имени
     */
    @Test
    void getUserByName() throws UserNotFoundException {
        User expectedUser = new User();
        expectedUser.setName("testUser");
        when(userRepository.findByName("testUser")).thenReturn(Optional.of(expectedUser));

        User actualUser = userService.getUserByName("testUser");

        assertNotNull(actualUser);
        assertEquals("testUser", actualUser.getName());
    }

    /**
     * Тест ошибки при получении несуществующего пользователя
     */
    @Test
    void getUserByNameNotFound() {
        when(userRepository.findByName("unknown")).thenReturn(Optional.empty());

        Exception e = assertThrows(UserNotFoundException.class, () -> userService.getUserByName("unknown"));
        assertEquals("Пользователь с именем unknown не найден!", e.getMessage());
    }

    /**
     * Тест удаления пользователя со всеми связанными сущностями
     */
    @Test
    void deleteUser() throws UserNotFoundException {
        User user = new User();
        user.setName("testUser");

        Task task = new Task();
        Comment comment = new Comment();
        Project project = new Project();

        when(userRepository.findByName("testUser")).thenReturn(Optional.of(user));
        when(transactionManager.getTransaction(any())).thenReturn(transactionStatus);
        when(taskRepository.findTasksByUser(user)).thenReturn(List.of(task));
        when(commentRepository.findCommentsByUser(user)).thenReturn(List.of(comment));
        when(projectRepository.findProjectsByUser(user)).thenReturn(List.of(project));

        userService.deleteUser("testUser");

        verify(userRepository).delete(user);
        verify(taskRepository).deleteAll(List.of(task));
        verify(commentRepository).deleteAll(List.of(comment));
        verify(projectRepository).deleteAll(List.of(project));
        verify(transactionManager).commit(transactionStatus);
    }

    /**
     * Тест ошибки при удалении пользователя (откат транзакции)
     */
    @Test
    void deleteUserFail() {
        User user = new User();
        user.setName("testUser");

        Task task = new Task();
        Comment comment = new Comment();
        Project project = new Project();

        when(userRepository.findByName("testUser")).thenReturn(Optional.of(user));
        when(transactionManager.getTransaction(any())).thenReturn(transactionStatus);
        when(taskRepository.findTasksByUser(user)).thenReturn(List.of(task));
        when(commentRepository.findCommentsByUser(user)).thenReturn(List.of(comment));
        when(projectRepository.findProjectsByUser(user)).thenReturn(List.of(project));
        doThrow(new DataAccessException("Error") {}).when(taskRepository).deleteAll(anyList());

        assertThrows(DataAccessException.class, () -> userService.deleteUser("testUser"));

        verify(transactionManager).rollback(transactionStatus);
        verify(userRepository, never()).delete(user);
    }
}