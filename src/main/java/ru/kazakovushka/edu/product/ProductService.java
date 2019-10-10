package ru.kazakovushka.edu.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAllProducts() {
        return repository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    public Product save(Product stock){
       return repository.save(stock);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

}
