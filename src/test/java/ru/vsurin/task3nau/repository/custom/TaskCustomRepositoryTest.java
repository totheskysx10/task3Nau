package ru.vsurin.task3nau.repository.custom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vsurin.task3nau.domain.Project;
import ru.vsurin.task3nau.domain.Status;
import ru.vsurin.task3nau.domain.Task;
import ru.vsurin.task3nau.domain.User;
import ru.vsurin.task3nau.repository.ProjectRepository;
import ru.vsurin.task3nau.repository.TaskRepository;
import ru.vsurin.task3nau.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskCustomRepositoryTest {

    private final TaskCustomRepository taskCustomRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    TaskCustomRepositoryTest(TaskCustomRepository taskCustomRepository,
                             TaskRepository taskRepository,
                             UserRepository userRepository,
                             ProjectRepository projectRepository) {
        this.taskCustomRepository = taskCustomRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    /**
     * Тест поиска задач по части названия
     */
    @Test
    void findByTitleContaining() {
        User user = new User();
        userRepository.save(user);
        Project project = new Project("p", "d", new Date(), user, new ArrayList<>());
        projectRepository.save(project);

        Task task1 = new Task("task1", Status.PLANNED, 1, project, user, new ArrayList<>(), new ArrayList<>());
        taskRepository.save(task1);

        Task task2 = new Task("task2", Status.PLANNED, 1, project, user, new ArrayList<>(), new ArrayList<>());
        taskRepository.save(task2);

        Task task3 = new Task("t", Status.PLANNED, 1, project, user, new ArrayList<>(), new ArrayList<>());
        taskRepository.save(task3);

        List<Task> foundTasks = taskCustomRepository.findByTitleContaining("task");

        assertEquals(2, foundTasks.size());
        assertEquals("task1", foundTasks.get(0).getTitle());
        assertEquals("task2", foundTasks.get(1).getTitle());
    }
}