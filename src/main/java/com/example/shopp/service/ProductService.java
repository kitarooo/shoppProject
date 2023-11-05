package com.example.shopp.service;

import com.example.shopp.dto.ProductRequest;
import com.example.shopp.entity.Category;
import com.example.shopp.entity.Product;
import com.example.shopp.entity.User;
import com.example.shopp.exception.NotFoundException;
import com.example.shopp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product checkProductOnExistAndReturn(Long id) {
        return productRepository.findAllById(id).orElseThrow(
                () -> new NotFoundException("Product was not found!"));
    }

    public Product parseToProduct(ProductRequest request) {
        return Product.builder().productName(request.getProductName()).
                productPrice(request.getProductPrice()).
                description(request.getDescription()).build();
    }
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public ResponseEntity<Object> createProduct(ProductRequest productRequest) {
        Model model = new Model();

        if (productRepository.findAllByProductName(productRequest.getProductName()).isPresent()) {
            model.setResult("Product already exist!");
            return ResponseEntity.ok(model.getResult());
        }
        productRepository.save(parseToProduct(productRequest));
        model.setResult("Product was created!");

        return ResponseEntity.ok(model.getResult());
    }

    public ProductRequest getProductById(Long id) {
        Product product = checkProductOnExistAndReturn(id);
        return ProductRequest.builder().productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .description(product.getDescription()).build();
    }

    public ResponseEntity<Object> updateProductById(Long id, ProductRequest productRequest) {
        Model model = new Model();
        Product product = checkProductOnExistAndReturn(id);

        product.setProductName(productRequest.getProductName());
        product.setProductPrice(productRequest.getProductPrice());
        product.setDescription(productRequest.getDescription());
        product.setCategory(product.getCategory());
        productRepository.save(product);
        model.setResult("Product was updated!");
        return ResponseEntity.ok(model.getResult());
    }

    public ResponseEntity<Object> deleteProductById(Long id) {
        Model model = new Model();
        productRepository.deleteById(id);
        model.setResult("Product was deleted!");
        return ResponseEntity.ok(model.getResult());
    }
}
