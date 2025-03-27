package ru.vsurin.task3nau.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsurin.task3nau.domain.Comment;
import ru.vsurin.task3nau.domain.Task;
import ru.vsurin.task3nau.repository.custom.CommentCustomRepository;

import java.util.List;

/**
 * REST-контроллер кастомных операций для комментов
 */
@RestController
@RequestMapping("/custom/comments")
public class CommentController {

    private final CommentCustomRepository commentCustomRepository;

    public CommentController(CommentCustomRepository commentCustomRepository) {
        this.commentCustomRepository = commentCustomRepository;
    }

    /**
     * Ищет комментарии по задаче и текстовому отрывку
     * @param textFragment отрывок текста
     * @param task задача
     */
    @GetMapping("/search")
    public ResponseEntity<List<Comment>> findCommentsByTaskAndTextFragment(@RequestParam String textFragment, @ModelAttribute Task task) {
        List<Comment> comments = commentCustomRepository.findCommentsByTaskAndTextFragment(textFragment, task);

        if (comments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(comments);
    }
}
