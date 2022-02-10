package ru.smurtazin.akvelon.newtests.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/*
* insert into products(id, name, rating) values
*   (1, "porage", 5),
*   (2, "juice", 3),
*   ;
* */

@Data
@Entity(name = "Product")
@Table(name = "products")
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String name;
    String description;
//    List<Characteristic> characteristics;

    @OneToMany
    @JoinColumn(name = "comment_id")
    List<Comment> comments;

    int rating;

//    public static enum Characteristic {
//        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
//    }

}
