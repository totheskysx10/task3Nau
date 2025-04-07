package ru.vsurin.task3nau.assembler;

import org.springframework.stereotype.Component;
import ru.vsurin.task3nau.domain.Comment;
import ru.vsurin.task3nau.response.CommentDto;

/**
 * Ассемблер комментариев
 */
@Component
public class CommentAssembler {

    /**
     * Преобразование из Comment в CommentDto
     */
    public CommentDto toDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getText(),
                comment.getCreatedAt(),
                comment.getAuthor().getId(),
                comment.getTask().getId()
        );
    }
}
