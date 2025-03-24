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
class AttachmentRepositoryTest {

    private final AttachmentRepository attachmentRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Autowired
    AttachmentRepositoryTest(AttachmentRepository attachmentRepository,
                             UserRepository userRepository,
                             ProjectRepository projectRepository,
                             TaskRepository taskRepository) {
        this.attachmentRepository = attachmentRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    /**
     * Тест поиска вложений задачи
     */
    @Test
    void findAttachmentsByTask() {
        User user = new User();
        userRepository.save(user);
        Project project = new Project("p", "d", new Date(), user, new ArrayList<>());
        projectRepository.save(project);
        Task task1 = new Task("task1", Status.PLANNED, 1, project, user, new ArrayList<>(), new ArrayList<>());
        taskRepository.save(task1);
        Task task2 = new Task("task2", Status.PLANNED, 1, project, user, new ArrayList<>(), new ArrayList<>());
        taskRepository.save(task2);

        Attachment attachment1 = new Attachment("attachment1", "url", task1);
        attachmentRepository.save(attachment1);

        Attachment attachment2 = new Attachment("attachment2", "url", task1);
        attachmentRepository.save(attachment2);

        Attachment attachment3 = new Attachment("attachment3", "url", task2);
        attachmentRepository.save(attachment3);

        List<Attachment> foundAttachments = attachmentRepository.findAttachmentsByTask(task1);

        assertEquals(2, foundAttachments.size());
        assertEquals("attachment1", foundAttachments.get(0).getFileName());
        assertEquals("attachment2", foundAttachments.get(1).getFileName());
    }
}