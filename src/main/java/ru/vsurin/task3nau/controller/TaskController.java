package ru.vsurin.task3nau.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsurin.task3nau.assembler.TaskAssembler;
import ru.vsurin.task3nau.domain.Project;
import ru.vsurin.task3nau.domain.Task;
import ru.vsurin.task3nau.exception.TaskNotFoundException;
import ru.vsurin.task3nau.repository.custom.TaskCustomRepository;
import ru.vsurin.task3nau.response.TaskDto;
import ru.vsurin.task3nau.service.TaskService;

import java.util.List;

/**
 * REST-контроллер кастомных операций для задач
 */
@RestController
@RequestMapping("/custom/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskAssembler taskAssembler;

    public TaskController(TaskService taskService, TaskAssembler taskAssembler) {
        this.taskService = taskService;
        this.taskAssembler = taskAssembler;
    }

    /**
     * Найти задачи по проекту и отрывку названия
     * @param titleFragment отрывок названия
     * @param project проект
     */
    @GetMapping("/search")
    public ResponseEntity<List<TaskDto>> findByProjectAndTitleContaining(@RequestParam String titleFragment, @ModelAttribute Project project) {
        List<Task> tasks = taskService.findByProjectAndTitleContaining(titleFragment, project);

        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<TaskDto> result = tasks.stream()
                .map(taskAssembler::toDto)
                .toList();

        return ResponseEntity.ok(result);
    }

    /**
     * Удаляет задачу и все связанные комментарии
     * @param id идентификатор
     */
    @DeleteMapping("/del-task-with-comments")
    public ResponseEntity<Void> deleteTaskWithComments(@RequestParam Long id) throws TaskNotFoundException {
        taskService.deleteTaskWithComments(id);
        return ResponseEntity.ok().build();
    }
}
