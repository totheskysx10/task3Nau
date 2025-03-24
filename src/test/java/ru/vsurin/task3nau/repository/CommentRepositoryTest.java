package ru.vsurin.task3nau.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vsurin.task3nau.domain.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentRepositoryTest {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Autowired
    CommentRepositoryTest(CommentRepository commentRepository,
                          UserRepository userRepository,
                          ProjectRepository projectRepository,
                          TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    /**
     * Тест поиска комментов задачи
     */
    @Test
    void findCommentsByTask() {
        User user = new User();
        userRepository.save(user);
        Project project = new Project("p", "d", new Date(), user, new ArrayList<>());
        projectRepository.save(project);
        Task task1 = new Task("task1", Status.PLANNED, 1, project, user, new ArrayList<>(), new ArrayList<>());
        taskRepository.save(task1);
        Task task2 = new Task("task2", Status.PLANNED, 1, project, user, new ArrayList<>(), new ArrayList<>());
        taskRepository.save(task2);

        Comment comment1 = new Comment("comment1", new Date(), user, task1);
        commentRepository.save(comment1);

        Comment comment2 = new Comment("comment2", new Date(), user, task1);
        commentRepository.save(comment2);

        Comment comment3 = new Comment("c", new Date(), user, task2);
        commentRepository.save(comment3);

        List<Comment> foundComments = commentRepository.findCommentsByTask(task1);

        assertEquals(2, foundComments.size());
        assertEquals("comment1", foundComments.get(0).getText());
        assertEquals("comment2", foundComments.get(1).getText());
    }
}