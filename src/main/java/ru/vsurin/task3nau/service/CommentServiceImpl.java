package ru.vsurin.task3nau.service;

import org.springframework.stereotype.Service;
import ru.vsurin.task3nau.domain.Comment;
import ru.vsurin.task3nau.domain.Task;
import ru.vsurin.task3nau.repository.custom.CommentCustomRepository;

import java.util.List;

/**
 * Реализация сервиса комментариев
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentCustomRepository commentCustomRepository;

    public CommentServiceImpl(CommentCustomRepository commentCustomRepository) {
        this.commentCustomRepository = commentCustomRepository;
    }

    @Override
    public List<Comment> findCommentsByTaskAndTextFragment(String textFragment, Task task) {
        return commentCustomRepository.findCommentsByTaskAndTextFragment(textFragment, task);
    }
}
