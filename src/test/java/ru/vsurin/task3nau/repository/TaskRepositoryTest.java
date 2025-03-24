package ru.vsurin.task3nau.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vsurin.task3nau.domain.Project;
import ru.vsurin.task3nau.domain.Status;
import ru.vsurin.task3nau.domain.Task;
import ru.vsurin.task3nau.domain.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskRepositoryTest {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    TaskRepositoryTest(TaskRepository taskRepository,
                       UserRepository userRepository,
                       ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    /**
     * Тест поиска задач по статусу и приоритету
     */
    @Test
    void findByStatusAndPriority() {
        User user = new User();
        userRepository.save(user);
        Project project = new Project("p", "d", new Date(), user, new ArrayList<>());
        projectRepository.save(project);

        Task task1 = new Task("task1", Status.PLANNED, 1, project, user, new ArrayList<>(), new ArrayList<>());
        taskRepository.save(task1);

        Task task2 = new Task("task2", Status.IN_PROGRESS, 2, project, user, new ArrayList<>(), new ArrayList<>());
        taskRepository.save(task2);

        Task task3 = new Task("task3", Status.IN_PROGRESS, 3, project, user, new ArrayList<>(), new ArrayList<>());
        taskRepository.save(task3);

        List<Task> tasks = taskRepository.findByStatusAndPriority(Status.IN_PROGRESS, 3);

        assertEquals(1, tasks.size());
        assertEquals("task3", tasks.get(0).getTitle());
    }
}