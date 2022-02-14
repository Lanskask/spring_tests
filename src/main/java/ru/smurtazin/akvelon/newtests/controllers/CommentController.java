package ru.smurtazin.akvelon.newtests.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.smurtazin.akvelon.newtests.exceptions.ItemNotFoundException;
import ru.smurtazin.akvelon.newtests.models.Comment;
import ru.smurtazin.akvelon.newtests.repositories.CommentRepository;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {
    private final CommentRepository commentRepo;

    CommentController(CommentRepository commentRepo) {
        this.commentRepo = commentRepo;
    }

    @GetMapping("/comments")
    Iterable<Comment> allComments() {
        return commentRepo.findAll();
    }

    @PostMapping("/comments")
    Comment newComment(@RequestBody Comment comment) {
        return commentRepo.save(comment);
    }

    @GetMapping("/comments/{id}")
    Comment getCommentById(@PathVariable Long id) {
        return commentRepo.findById(id).orElseThrow(
                () -> new ItemNotFoundException(id)
        );
    }

    @PutMapping("/comment/{id}")
    Comment replaceComment(@RequestBody Comment newComment, @PathVariable Long id) {
        return commentRepo.findById(id)
                .map(foundComment -> {
                    foundComment.setText(newComment.getText());
                    return commentRepo.save(foundComment);
                })
                .orElseGet(() -> {
                    newComment.setId(id);
                    return commentRepo.save(newComment);
                });
    }

    @DeleteMapping("/comment/{id}")
    void deleteComment(@PathVariable Long id) {
        commentRepo.deleteById(id);
    }

}
