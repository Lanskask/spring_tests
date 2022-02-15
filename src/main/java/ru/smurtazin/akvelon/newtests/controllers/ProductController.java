package ru.smurtazin.akvelon.newtests.controllers;

import org.springframework.web.bind.annotation.*;
import ru.smurtazin.akvelon.newtests.exceptions.ItemNotFoundException;
import ru.smurtazin.akvelon.newtests.models.Product;
import ru.smurtazin.akvelon.newtests.repositories.ProductRepository;

@RestController
public class ProductController {
    private final ProductRepository repo;

    ProductController(ProductRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/products")
    Iterable<Product> allProducts() {
        return repo.findAll();
    }

    @PostMapping("/products")
    Product newProduct(@RequestBody Product item) {
        return repo.save(item);
    }

    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(
                () -> new ItemNotFoundException("product", id)
        );
    }

    @PutMapping("/product/{id}")
    Product updateProduct(@RequestBody Product newItem, @PathVariable Long id) {
        return repo.findById(id)
                .map(foundItem -> {
                    foundItem.setName(newItem.getName());
                    foundItem.setDescription(newItem.getDescription());
//                    foundItem.setCharacteristics(newItem.getCharacteristics());
                    foundItem.setRating(newItem.getRating());
//                    foundItem.setComments(newItem.getComments());
                    return repo.save(foundItem);
                })
                .orElseGet(() -> {
                    newItem.setId(id);
                    return repo.save(newItem);
                });
    }

    @DeleteMapping("/product/{id}")
    void deleteProduct(@PathVariable Long id) {
        repo.deleteById(id);
    }

}
