package ru.smurtazin.akvelon.newtests.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.smurtazin.akvelon.newtests.models.Comment;
import ru.smurtazin.akvelon.newtests.repositories.CommentRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CommentControllerTest {

    List<Comment> comments;

    @Mock
    CommentRepository repo;

//    @Autowired
//    private MockMvc mockMvc;

    @Autowired
    @InjectMocks
    private CommentController controller;

//    @BeforeEach
    void setUp() {
        var commChromebook1 = Comment.builder()
//                    .id(1L)
                .text("This Chromebook is worse than ever")
                .productId(1L)
                .build();
        var commChromebook2 = Comment.builder()
//                    .id(2L)
                .text("Could be worse")
                .productId(1L)
                .build();
        var commMacbook1 = Comment.builder()
//                    .id(2L)
                .text("Too much hype")
                .productId(2L)
                .build();
        var commMacbook2 = Comment.builder()
//                    .id(2L)
                .text("Hope they will add more ports then two")
                .productId(2L)
                .build();

        this.comments = List.of(commMacbook1, commMacbook2, commChromebook1, commChromebook2);
    }

//    @AfterEach
    void tearDown() {
        this.comments = Collections.emptyList();
    }

    @Test
    void allComments() {
        var commChromebook1 = Comment.builder()
//                    .id(1L)
                .text("This Chromebook is worse than ever")
                .productId(1L)
                .build();
        var commChromebook2 = Comment.builder()
//                    .id(2L)
                .text("Could be worse")
                .productId(1L)
                .build();
        var comments = List.of(commChromebook1, commChromebook2);

        lenient().when(repo.findAll()).thenReturn(comments);

        var res = controller.allComments();

//        Assertions.assertEquals(this.comments, List.of(res));
        var expectedTexts = comments.stream()
                .map(Comment::getText)
                .collect(Collectors.toList());

        var resultTexts = StreamSupport
                .stream(res.spliterator(), false)
                .map(Comment::getText).collect(Collectors.toList());

        Assertions.assertTrue(resultTexts.containsAll(expectedTexts));
    }

    @Test
    void newComment() {

    }

    @Test
    void getCommentById() {
    }

    @Test
    void replaceComment() {
    }

    @Test
    void deleteComment() {
    }
}