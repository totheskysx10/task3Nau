package ru.vsurin.task3nau.repository.custom;

import ru.vsurin.task3nau.domain.Comment;

import java.util.List;

/**
 * Кастомный репозиторий комментариев
 */
public interface CommentCustomRepository {

    /**
     * Ищет комментарии по текстовому отрывку
     * @param textFragment отрывок текста
     */
    List<Comment> findCommentsByTextFragment(String textFragment);
}
