package com.example.shopp.controller;

import com.example.shopp.dto.ProductRequest;
import com.example.shopp.entity.Product;
import com.example.shopp.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/allProducts")
    public List<Product> findAllProducts() {
        return productService.findAllProducts();
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Object> createProduct(ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @GetMapping("{id}")
    public ProductRequest findProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateProductById(@PathVariable Long id, ProductRequest productRequest) {
        return productService.updateProductById(id, productRequest);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProductById(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }
}
