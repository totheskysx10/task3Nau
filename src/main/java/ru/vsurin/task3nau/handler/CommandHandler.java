package ru.vsurin.task3nau.handler;

import org.springframework.stereotype.Component;
import ru.vsurin.task3nau.domain.Status;
import ru.vsurin.task3nau.domain.Task;
import ru.vsurin.task3nau.exception.TaskDuplicateException;
import ru.vsurin.task3nau.exception.TaskNotFoundException;
import ru.vsurin.task3nau.service.TaskService;

import java.util.List;

/**
 * Обработчик команд
 */
@Component
public class CommandHandler {

    private final TaskService taskService;

    public CommandHandler(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Обработать команду
     * @param input команда
     */
    public void handleCommand(String input){
        String[] cmd = input.split(" ");
        switch (cmd[0]) {
            case "/create" -> {
                try {
                    taskService.createTask(Long.valueOf(cmd[1]), cmd[2], Status.valueOf(cmd[3]));
                    System.out.println("Задача успешно создана");
                } catch (TaskDuplicateException e) {
                    System.out.println(e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Неверные данные!");
                }
            }
            case "/get" -> {
                try {
                    Task task = taskService.findById(Long.valueOf(cmd[1]));
                    System.out.println(task);
                } catch (TaskNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Неверные данные!");
                }
            }
            case "/update-status" -> {
                try {
                    taskService.updateTaskStatus(Long.valueOf(cmd[1]), Status.valueOf(cmd[2]));
                    System.out.println("Статус обновлён");
                } catch (TaskNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Неверные данные!");
                }
            }
            case "/del" -> {
                try {
                    taskService.deleteById(Long.valueOf(cmd[1]));
                    System.out.println("Задача удалена");
                } catch (IllegalArgumentException e) {
                    System.out.println("Неверные данные!");
                }
            }
            case "/get-all" -> {
                List<Task> tasks = taskService.findAll();
                System.out.println(tasks);
            }
            default -> System.out.println("Введена неизвестная команда...");
        }
    }
}

