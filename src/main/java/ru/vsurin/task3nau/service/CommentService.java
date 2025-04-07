package ru.vsurin.task3nau.service;

import ru.vsurin.task3nau.domain.Comment;
import ru.vsurin.task3nau.domain.Task;

import java.util.List;

/**
 * Сервис комментариев
 */
public interface CommentService {

    /**
     * Ищет комментарии по задаче и текстовому отрывку
     * @param textFragment отрывок текста
     * @param task задача
     */
    List<Comment> findCommentsByTaskAndTextFragment(String textFragment, Task task);
}
