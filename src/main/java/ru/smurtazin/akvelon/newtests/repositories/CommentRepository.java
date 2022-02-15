package ru.smurtazin.akvelon.newtests.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.smurtazin.akvelon.newtests.models.Comment;


public interface CommentRepository extends CrudRepository<Comment, Long> {

    @Override
    Iterable<Comment> findAll();
}
