package ru.vsurin.task3nau.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vsurin.task3nau.domain.Task;
import ru.vsurin.task3nau.repository.TaskRepository;

/**
 * Контроллер для отображения задач
 */
@Controller
@RequestMapping("/tasks/custom/view")
public class TaskControllerView {

    private final TaskRepository taskRepository;

    public TaskControllerView(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Отображает задачи в виде таблицы
     * @param model модель
     */
    @GetMapping("/list")
    public String taskListView(Model model)
    {
        Iterable<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "taskList";
    }
}
