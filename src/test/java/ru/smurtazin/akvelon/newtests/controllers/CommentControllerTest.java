package ru.smurtazin.akvelon.newtests.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.smurtazin.akvelon.newtests.models.Comment;
import ru.smurtazin.akvelon.newtests.repositories.CommentRepository;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class CommentControllerTest {

    List<Comment> comments;

    @MockBean
    CommentRepository repo;

    @Autowired
    private CommentController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        var commChromebook1 = Comment.builder()
                .id(1L)
                .text("This Chromebook is worse than ever")
                .productId(1L)
                .build();
        var commChromebook2 = Comment.builder()
                .id(2L)
                .text("Could be worse")
                .productId(1L)
                .build();
        var commMacbook1 = Comment.builder()
                .id(2L)
                .text("Too much hype")
                .productId(2L)
                .build();
        var commMacbook2 = Comment.builder()
                .id(2L)
                .text("Hope they will add more ports then two")
                .productId(2L)
                .build();

        this.comments = List.of(commMacbook1, commMacbook2, commChromebook1, commChromebook2);
    }

    @AfterEach
    void tearDown() {
        this.comments = Collections.emptyList();
        Mockito.reset(repo);
    }

    @Test
        //    @Disabled // I don't know why repo mocking don't happens
    void allComments() {
        when(repo.findAll()).thenReturn(comments);

        var res = controller.allComments();

        assertThat(this.comments, is(res));
    }

    @Test
    void newComment() {
//        var repo = mock(CommentRepository.class);
        var commChromebook2 = Comment.builder()
                .id(2L)
                .text("Could be worse")
                .productId(1L)
                .build();

        controller.newComment(commChromebook2);
        verify(repo).save(commChromebook2);
    }

    @Test
    public void aLittleTest() {
        var commChromebook2 = Comment.builder()
                .id(2L)
                .text("Could be worse")
                .productId(1L)
                .build();

        Mockito.when(repo.save(commChromebook2)).thenReturn(commChromebook2);

        var res = controller.newComment(commChromebook2);

        assertEquals(commChromebook2, res);
        Mockito.verify(repo).save(commChromebook2);
    }
}