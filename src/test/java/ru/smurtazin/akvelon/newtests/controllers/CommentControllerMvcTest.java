package ru.smurtazin.akvelon.newtests.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.smurtazin.akvelon.newtests.models.Comment;
import ru.smurtazin.akvelon.newtests.repositories.CommentRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
@ActiveProfiles("test")
class CommentControllerMvcTest {

    List<Comment> comments;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CommentRepository repo;

    @BeforeEach
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

        this.comments = List.of(commChromebook1, commChromebook2, commMacbook1, commMacbook2);
    }

    @AfterEach
    void tearDown() {
        this.comments = Collections.emptyList();
    }

    @Test
    void allComments() throws Exception {
        lenient().when(repo.findAll()).thenReturn(this.comments);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/comments")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].text", is("This Chromebook is worse than ever")))
                .andExpect(jsonPath("$[0].product_id", is(1)))
                .andExpect(jsonPath("$[1].text", is("Could be worse")))
                .andExpect(jsonPath("$[1].product_id", is(1)))
        ;
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