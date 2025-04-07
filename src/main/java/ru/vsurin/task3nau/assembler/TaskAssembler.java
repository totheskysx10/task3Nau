package ru.vsurin.task3nau.assembler;

import org.springframework.stereotype.Component;
import ru.vsurin.task3nau.domain.*;
import ru.vsurin.task3nau.response.TaskDto;

/**
 * Ассемблер задач
 */
@Component
public class TaskAssembler {

    /**
     * Преобразование из Task в TaskDto
     */
    public TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getStatus().name(),
                task.getPriority(),
                task.getProject().getId(),
                task.getAssignedUser().getId(),

                task.getComments().stream()
                        .map(Comment::getId)
                        .toList(),

                task.getAttachments().stream()
                        .map(Attachment::getId)
                        .toList()
        );
    }
}
