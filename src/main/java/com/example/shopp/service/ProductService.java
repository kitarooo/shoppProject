package com.example.shopp.service;

import com.example.shopp.model.Category;
import com.example.shopp.model.Product;
import com.example.shopp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    public Product findProductById(Long id) {
       return productRepository.findAllById(id).orElse(null);
    }

    public Product updateProductById(Long id, String product_name, double product_price, String description, Category category) {
        Product product = productRepository.findAllById(id).orElse(null);

        if (product != null) {
            product.setProduct_name(product_name);
            product.setProduct_price(product_price);
            product.setDescription(description);
            product.setCategory(category);
            productRepository.save(product);
        }

        return product;
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
