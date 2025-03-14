package ru.vsurin.task3nau.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.vsurin.task3nau.configuration.AppConfig;
import ru.vsurin.task3nau.domain.Status;
import ru.vsurin.task3nau.domain.Task;
import ru.vsurin.task3nau.exception.TaskDuplicateException;
import ru.vsurin.task3nau.exception.TaskNotFoundException;
import ru.vsurin.task3nau.repository.TaskRepository;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final AppConfig appConfig;

    public TaskServiceImpl(TaskRepository taskRepository, AppConfig appConfig) {
        this.taskRepository = taskRepository;
        this.appConfig = appConfig;
    }

    /**
     * Выводит в консоль данные о приложении
     */
    @PostConstruct
    void printAppInfo() {
        String appName = appConfig.getAppName();
        String appVersion = appConfig.getAppVersion();

        System.out.println("AppName: " + appName + ", AppVersion: " + appVersion);
    }

    @Override
    public void createTask(Long id, String title, Status status) throws TaskDuplicateException {
        if (taskRepository.existsById(id)) {
            throw new TaskDuplicateException("Задача с id " + id + " существует!");
        }
        Task task = new Task(id, title, status);
        taskRepository.create(task);
    }

    @Override
    public Task findById(Long id) throws TaskNotFoundException {
        Task task = taskRepository.read(id);
        if (task == null) {
            throw new TaskNotFoundException("Задача с id " + id + " не найдена!");
        }

        return task;
    }

    @Override
    public void updateTaskStatus(Long id, Status status) throws TaskNotFoundException {
        Task task = findById(id);
        task.setStatus(status);
        taskRepository.update(task);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.delete(id);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }
}
