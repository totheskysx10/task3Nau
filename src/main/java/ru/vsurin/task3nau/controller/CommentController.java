package ru.vsurin.task3nau.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsurin.task3nau.domain.Comment;
import ru.vsurin.task3nau.domain.Task;
import ru.vsurin.task3nau.assembler.CommentAssembler;
import ru.vsurin.task3nau.response.CommentDto;
import ru.vsurin.task3nau.service.CommentService;

import java.util.List;

/**
 * REST-контроллер кастомных операций для комментов
 */
@RestController
@RequestMapping("/custom/comments")
public class CommentController {

    private final CommentService commentService;
    private final CommentAssembler commentAssembler;

    public CommentController(CommentService commentService, CommentAssembler commentAssembler) {
        this.commentService = commentService;
        this.commentAssembler = commentAssembler;
    }

    /**
     * Ищет комментарии по задаче и текстовому отрывку
     * @param textFragment отрывок текста
     * @param task задача
     */
    @GetMapping("/search")
    public ResponseEntity<List<CommentDto>> findCommentsByTaskAndTextFragment(@RequestParam String textFragment, @ModelAttribute Task task) {
        List<Comment> comments = commentService.findCommentsByTaskAndTextFragment(textFragment, task);

        if (comments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<CommentDto> result = comments.stream()
                .map(commentAssembler::toDto)
                .toList();

        return ResponseEntity.ok(result);
    }
}
