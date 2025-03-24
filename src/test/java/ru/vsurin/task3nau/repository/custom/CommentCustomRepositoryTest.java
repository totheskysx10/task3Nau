package ru.vsurin.task3nau.repository.custom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vsurin.task3nau.domain.*;
import ru.vsurin.task3nau.repository.CommentRepository;
import ru.vsurin.task3nau.repository.ProjectRepository;
import ru.vsurin.task3nau.repository.TaskRepository;
import ru.vsurin.task3nau.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
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
     * Тест поиска комментариев по отрывку текста
     */
    @Test
    void findCommentsByTextFragment() {
        User user = new User();
        userRepository.save(user);
        Project project = new Project("p", "d", new Date(), user, new ArrayList<>());
        projectRepository.save(project);
        Task task = new Task("task1", Status.PLANNED, 1, project, user, new ArrayList<>(), new ArrayList<>());
        taskRepository.save(task);

        Comment comment1 = new Comment("comment1", new Date(), user, task);
        commentRepository.save(comment1);

        Comment comment2 = new Comment("comment2", new Date(), user, task);
        commentRepository.save(comment2);

        Comment comment3 = new Comment("c", new Date(), user, task);
        commentRepository.save(comment3);

        List<Comment> foundComments = commentCustomRepository.findCommentsByTextFragment("comment");

        assertEquals(2, foundComments.size());
        assertEquals("comment1", foundComments.get(0).getText());
        assertEquals("comment2", foundComments.get(1).getText());
    }
}