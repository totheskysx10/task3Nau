package ru.vsurin.task3nau.service;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.vsurin.task3nau.domain.*;
import ru.vsurin.task3nau.exception.UserDuplicateException;
import ru.vsurin.task3nau.exception.UserNotFoundException;
import ru.vsurin.task3nau.repository.CommentRepository;
import ru.vsurin.task3nau.repository.ProjectRepository;
import ru.vsurin.task3nau.repository.TaskRepository;
import ru.vsurin.task3nau.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Реализация сервиса пользователей
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PlatformTransactionManager transactionManager;
    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;
    private final ProjectRepository projectRepository;

    private final String ROLE_PREFIX = "ROLE_";

    public UserServiceImpl(UserRepository userRepository,
                           PlatformTransactionManager transactionManager,
                           TaskRepository taskRepository,
                           CommentRepository commentRepository,
                           ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.transactionManager = transactionManager;
        this.taskRepository = taskRepository;
        this.commentRepository = commentRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public void createUser(User user) throws UserDuplicateException {
        if (userRepository.existsByName(user.getName())) {
            throw new UserDuplicateException("Пользователь уже существует!");
        }

        user.setRoles(List.of(Role.USER));
        user.setCreationDate(LocalDate.now());
        user.setComments(new ArrayList<>());
        user.setOwnedProjects(new ArrayList<>());
        user.setTasks(new ArrayList<>());

        userRepository.save(user);
    }

    @Override
    public User getUserByName(String name) throws UserNotFoundException {
        Optional<User> user = userRepository.findByName(name);
        return user.orElseThrow(() -> new UserNotFoundException("Пользователь с именем " + name + " не найден!"));
    }

    @Override
    public void deleteUser(String name) throws UserNotFoundException {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            User user = getUserByName(name);

            List<Comment> comments = commentRepository.findCommentsByUser(user);
            commentRepository.deleteAll(comments);

            List<Project> projects = projectRepository.findProjectsByUser(user);
            projectRepository.deleteAll(projects);

            List<Task> tasks = taskRepository.findTasksByUser(user);
            taskRepository.deleteAll(tasks);

            userRepository.delete(user);
            transactionManager.commit(status);
        } catch (DataAccessException | UserNotFoundException e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByName(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), getUserRoles(user));
        } else {
            throw new UsernameNotFoundException("Пользователь с именем " + username + " не найден!");
        }
    }

    /**
     * Маппит роли пользователя в SimpleGrantedAuthority
     * @param user пользователь
     */
    private Collection<? extends GrantedAuthority> getUserRoles(User user) {
        List<Role> roles = user.getRoles();

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role))
                .toList();
    }
}
