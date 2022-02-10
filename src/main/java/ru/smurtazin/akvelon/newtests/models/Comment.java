package ru.smurtazin.akvelon.newtests.models;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "Comment")
@Table(name = "comments")
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;
}
