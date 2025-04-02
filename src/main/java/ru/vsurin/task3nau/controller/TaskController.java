package ru.vsurin.task3nau.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsurin.task3nau.domain.Project;
import ru.vsurin.task3nau.domain.Task;
import ru.vsurin.task3nau.exception.TaskNotFoundException;
import ru.vsurin.task3nau.repository.custom.TaskCustomRepository;
import ru.vsurin.task3nau.service.TaskService;

import java.util.List;

/**
 * REST-контроллер кастомных операций для задач
 */
@RestController
@RequestMapping("/custom/tasks")
public class TaskController {

    private final TaskCustomRepository taskCustomRepository;
    private final TaskService taskService;

    public TaskController(TaskCustomRepository taskCustomRepository, TaskService taskService) {
        this.taskCustomRepository = taskCustomRepository;
        this.taskService = taskService;
    }

    /**
     * Найти задачи по проекту и отрывку названия
     * @param titleFragment отрывок названия
     * @param project проект
     */
    @GetMapping("/search")
    public ResponseEntity<List<Task>> findByProjectAndTitleContaining(@RequestParam String titleFragment, @ModelAttribute Project project) {
        List<Task> tasks = taskCustomRepository.findByProjectAndTitleContaining(titleFragment, project);

        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(tasks);
    }

    /**
     * Удаляет задачу и все связанные комментарии
     * @param id идентификатор
     */
    @DeleteMapping("/del-task-with-comments")
    public ResponseEntity<Void> deleteTaskWithComments(@RequestParam Long id) {
        try {
            taskService.deleteTaskWithComments(id);
            return ResponseEntity.ok().build();
        } catch (TaskNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
