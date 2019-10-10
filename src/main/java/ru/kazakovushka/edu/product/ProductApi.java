package ru.kazakovushka.edu.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/products")
@Slf4j
public class ProductApi {
    private final ProductService productService;

    public ProductApi(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            System.out.println("there is no product with id " + id);
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(product.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody Product product) {
        if (!productService.findById(id).isPresent()) {
            System.out.println("nothing to update , no record with id = " + id);
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productService.save(product));
    }

    @DeleteMapping
    public ResponseEntity delete(@PathVariable Long id) {
        if (!productService.findById(id).isPresent()) {
            System.out.println("nothing to delete, no record with id =" + id);
            ResponseEntity.badRequest().build();
        }
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
