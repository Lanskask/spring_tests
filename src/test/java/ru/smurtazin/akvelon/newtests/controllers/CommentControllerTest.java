package ru.smurtazin.akvelon.newtests.controllers;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.smurtazin.akvelon.newtests.models.Comment;
import ru.smurtazin.akvelon.newtests.repositories.CommentRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class CommentControllerTest {

    List<Comment> comments;

    @Mock
    CommentRepository repo;

    @Autowired
    @InjectMocks
    private CommentController controller;

    @BeforeAll
    void berofeAll() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    void setUp() {
        Mockito.reset(repo);
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

    @AfterEach
    void tearDown() {
        this.comments = Collections.emptyList();
    }

    @Test
//    @Disabled
        // I don't know why repo mocking don't happens
    void allComments() {
//        var commChromebook1 = Comment.builder()
//                .id(1L)
//                .text("This Chromebook is worse than ever")
//                .productId(1L)
//                .build();
//        var commChromebook2 = Comment.builder()
//                .id(2L)
//                .text("Could be worse")
//                .productId(1L)
//                .build();
//        var comments = List.of(commChromebook1, commChromebook2);

//        lenient().when(repo.findAll()).thenReturn(comments);
//        var repo = mock(CommentRepository.class);
        when(repo.findAll()).thenReturn(comments);

        var res = controller.allComments();

//        Assertions.assertEquals(this.comments, List.of(res));
        var expectedTexts = comments.stream()
                .map(Comment::getText)
                .collect(Collectors.toList());

        var resultTexts = StreamSupport
                .stream(res.spliterator(), false)
                .map(Comment::getText).collect(Collectors.toList());

//        assertThat(resultTexts, hasItems(expectedTexts.toArray(new String[expectedTexts.size()])));
        assertThat(resultTexts, is(expectedTexts));
//        Assertions.assertTrue(resultTexts.containsAll(expectedTexts));

    }

    @Test
    void newComment() {
        var repo = mock(CommentRepository.class);
        var commChromebook2 = Comment.builder()
                .id(2L)
                .text("Could be worse")
                .productId(1L)
                .build();
//        repo.save(commChromebook2);

        controller.newComment(commChromebook2);
        verify(repo).save(commChromebook2);
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