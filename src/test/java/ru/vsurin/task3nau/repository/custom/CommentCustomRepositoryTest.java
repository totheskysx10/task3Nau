package ru.vsurin.task3nau.repository.custom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vsurin.task3nau.domain.*;
import ru.vsurin.task3nau.repository.CommentRepository;
import ru.vsurin.task3nau.repository.ProjectRepository;
import ru.vsurin.task3nau.repository.TaskRepository;
import ru.vsurin.task3nau.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentCustomRepositoryTest {

    private final CommentCustomRepository commentCustomRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    CommentCustomRepositoryTest(CommentCustomRepository commentCustomRepository,
                                CommentRepository commentRepository,
                                UserRepository userRepository,
                                TaskRepository taskRepository,
                                ProjectRepository projectRepository) {
        this.commentCustomRepository = commentCustomRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    /**
     * Тест поиска комментариев по задаче и отрывку текста
     */
    @Test
    void findCommentsByTaskAndTextFragment() {
        User user = new User();
        userRepository.save(user);
        Project project = new Project("p", "d", LocalDate.of(2010, 1, 1), user, new ArrayList<>());
        projectRepository.save(project);
        Task task1 = new Task("task1", Status.PLANNED, 1, project, user, new ArrayList<>(), new ArrayList<>());
        taskRepository.save(task1);
        Task task2 = new Task("task2", Status.PLANNED, 1, project, user, new ArrayList<>(), new ArrayList<>());
        taskRepository.save(task2);

        Comment comment1 = new Comment("comment1", LocalDate.of(2010, 1, 1), user, task1);
        commentRepository.save(comment1);

        Comment comment2 = new Comment("comment2", LocalDate.of(2010, 1, 1), user, task2);
        commentRepository.save(comment2);

        Comment comment3 = new Comment("c", LocalDate.of(2010, 1, 1), user, task2);
        commentRepository.save(comment3);

        List<Comment> foundComments = commentCustomRepository.findCommentsByTaskAndTextFragment("comment", task2);

        assertEquals(1, foundComments.size());
        assertEquals("comment2", foundComments.get(0).getText());
    }
}