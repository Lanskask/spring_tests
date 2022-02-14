package ru.smurtazin.akvelon.newtests.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String name;
    String description;
    int rating;

    @OneToMany(targetEntity = Comment.class)
//    @JoinColumn(name = "id", nullable=true)
    List<Comment> comments;

    public Product(String name, String description, int rating) {
        this.name = name;
        this.description = description;
        this.rating = rating;
    }
}
