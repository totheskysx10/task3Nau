package ru.vsurin.task3nau.repository;

import org.springframework.stereotype.Component;
import ru.vsurin.task3nau.domain.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class TaskRepository implements EntityRepository<Task, Long> {

    private final List<Task> taskContainer;
    private final AtomicLong autoIncrementIndex = new AtomicLong(0);

    public TaskRepository(List<Task> taskContainer) {
        this.taskContainer = taskContainer;
    }

    @Override
    public void create(Task task) {
        task.setId(autoIncrementIndex.incrementAndGet());
        taskContainer.add(task);
    }

    @Override
    public Optional<Task> read(Long id) {
        return taskContainer.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    @Override
    public void delete(Long id) {
        taskContainer.removeIf(task -> task.getId().equals(id));
    }

    @Override
    public void update(Task task) {
        delete(task.getId());
        taskContainer.add(task);
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(taskContainer);
    }

    @Override
    public boolean existsById(Long id) {
        return taskContainer.stream()
                .anyMatch(task -> task.getId().equals(id));
    }
}
