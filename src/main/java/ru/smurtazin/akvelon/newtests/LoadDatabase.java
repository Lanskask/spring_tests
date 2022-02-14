package ru.smurtazin.akvelon.newtests;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.smurtazin.akvelon.newtests.models.Comment;
import ru.smurtazin.akvelon.newtests.models.Product;
import ru.smurtazin.akvelon.newtests.repositories.CommentRepository;
import ru.smurtazin.akvelon.newtests.repositories.ProductRepository;

import java.util.List;

//@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(
            CommentRepository commRepo,
            ProductRepository prodRepo
            ) {

        return args -> {
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
            var comments = List.of(commChromebook1, commChromebook2, commMacbook1, commMacbook2);
            for (var comm : comments) {
                log.info("Preloading " + commRepo.save(comm));
            }
//            commRepo.findAll().forEach(emp -> log.info("Preloaded " + emp));

            var prod1 = Product.builder()
//                    .id(1L)
                    .name("Chromebook")
                    .description("Not so good notebook")
                    .comments(List.of(commChromebook1, commChromebook2))
                    .build();
            var prod2 = Product.builder()
//                    .id(2L)
                    .name("MacBook")
                    .description("Good notebook")
                    .comments(List.of(commMacbook1, commMacbook2))
                    .build();
            var products = List.of(prod1, prod2);
            for (var prod : products) {
                log.info("Preloading " + prodRepo.save(prod));
            }
//            prodRepo.findAll().forEach(item -> log.info("Preloaded " + item));

        };
    }
}