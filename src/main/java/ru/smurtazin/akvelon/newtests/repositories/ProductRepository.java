package ru.smurtazin.akvelon.newtests.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.smurtazin.akvelon.newtests.models.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByName(String name);
    List<Product> findByDescription(String description);
    List<Product> findByRating(int rating);

}
