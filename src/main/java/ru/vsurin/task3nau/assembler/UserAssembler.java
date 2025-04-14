package ru.vsurin.task3nau.assembler;

import org.springframework.stereotype.Component;
import ru.vsurin.task3nau.domain.*;
import ru.vsurin.task3nau.response.UserDto;

/**
 * Ассемблер пользователей
 */
@Component
public class UserAssembler {

    /**
     * Преобразование из User в UserDto
     */
    public UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),

                user.getRoles().stream()
                        .map(Role::name)
                                .toList(),

                user.getCreationDate(),

                user.getTasks().stream()
                        .map(Task::getId)
                        .toList(),

                user.getOwnedProjects().stream()
                        .map(Project::getId)
                        .toList(),

                user.getComments().stream()
                        .map(Comment::getId)
                        .toList()
        );
    }
}
