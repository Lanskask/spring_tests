package ru.smurtazin.akvelon.newtests.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import ru.smurtazin.akvelon.newtests.models.Comment;
import ru.smurtazin.akvelon.newtests.repositories.CommentRepository;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class CommentControllerMvcWRepoTest {

    List<Comment> comments;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WebTestClient webTestClient;

//    @LocalServerPort
//    private Integer port;

//    @Autowired
//    private MockMvc mvc;

    @Autowired
    CommentRepository repo;

    @Autowired
    CommentController controller;

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
        repo.saveAll(this.comments);
    }

    @AfterEach
    void tearDown() {
        this.comments = Collections.emptyList();
    }

    @Test
    void allComments() throws Exception {
        var resBodyContentSpec = webTestClient
                .get().uri("/comments")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody();

        resBodyContentSpec
                .jsonPath("$[0].text").isEqualTo("This Chromebook is worse than ever")
                .jsonPath("$[0].product_id").isEqualTo(1)
                .jsonPath("$[1].text").isEqualTo("Could be worse")
                .jsonPath("$[1].product_id").isEqualTo(1)
        ;

    }

}