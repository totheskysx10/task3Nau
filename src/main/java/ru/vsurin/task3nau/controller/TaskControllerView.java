package ru.vsurin.task3nau.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vsurin.task3nau.domain.Task;
import ru.vsurin.task3nau.repository.TaskRepository;
import ru.vsurin.task3nau.service.TaskService;

/**
 * Контроллер для отображения задач
 */
@Controller
@RequestMapping("/tasks/custom/view")
public class TaskControllerView {

    private final TaskService taskService;

    public TaskControllerView(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Отображает задачи в виде таблицы
     * @param model модель
     */
    @GetMapping("/list")
    public String taskListView(Model model)
    {
        Iterable<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "taskList";
    }
}
