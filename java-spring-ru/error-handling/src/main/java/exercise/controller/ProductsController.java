package exercise.controller;

import exercise.exception.ResourceNotFoundException;
import exercise.model.Product;
import exercise.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "")
    public List<Product> index() {
        return productRepository.findAll();
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // BEGIN
    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product show(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Product with id %d not found", id)));
    }

    @PutMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product update(@PathVariable Long id,
                          @RequestBody Product data) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Product with id %d not found", id)));

        product.setTitle(data.getTitle());
        product.setPrice(data.getPrice());
        return productRepository.save(product);
    }
    // END

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        productRepository.deleteById(id);
    }
}
