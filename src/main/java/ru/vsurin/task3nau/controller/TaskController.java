package ru.vsurin.task3nau.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsurin.task3nau.domain.Project;
import ru.vsurin.task3nau.domain.Task;
import ru.vsurin.task3nau.repository.custom.TaskCustomRepository;

import java.util.List;

/**
 * REST-контроллер кастомных операций для задач
 */
@RestController
@RequestMapping("/custom/tasks")
public class TaskController {

    private final TaskCustomRepository taskCustomRepository;

    public TaskController(TaskCustomRepository taskCustomRepository) {
        this.taskCustomRepository = taskCustomRepository;
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
}
