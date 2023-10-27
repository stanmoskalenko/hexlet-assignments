package exercise.controller;

import exercise.exception.ResourceNotFoundException;
import exercise.model.Product;
import exercise.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> index(
            @RequestParam(required = false) Integer min,
            @RequestParam(required = false) Integer max) {
        if (min == null && max == null) {
            return productRepository.findAll(Sort.by(Sort.Order.desc("price")));
        }

        return productRepository.findByPriceBetweenOrderByPriceDesc(min, max);
    }
    // END

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

    }
}
