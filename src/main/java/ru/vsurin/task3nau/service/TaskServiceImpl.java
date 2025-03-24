package ru.vsurin.task3nau.service;

import org.springframework.stereotype.Service;
import ru.vsurin.task3nau.domain.Status;
import ru.vsurin.task3nau.domain.Task;
import ru.vsurin.task3nau.exception.TaskNotFoundException;
import ru.vsurin.task3nau.repository.TaskRepository;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void createTask(String title, Status status) {
        Task task = new Task(title, status);
        taskRepository.create(task);
    }

    @Override
    public Task findById(Long id) throws TaskNotFoundException {
        return taskRepository.read(id).orElseThrow(() -> new TaskNotFoundException("Задача с id " + id + " не найдена!"));
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
