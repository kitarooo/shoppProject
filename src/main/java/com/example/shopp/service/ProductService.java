package com.example.shopp.service;

import com.example.shopp.dto.info.ProductInfo;
import com.example.shopp.dto.request.ProductRequest;
import com.example.shopp.entity.Product;
import com.example.shopp.exception.NotFoundException;
import com.example.shopp.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    private int allQuantity = 0;
    private double totalPrice = 0;

    public Product checkProductOnExistAndReturn(Long id) {
        return productRepository.findAllByProductId(id).orElseThrow(
                () -> new NotFoundException("Product was not found!"));
    }

    public Product parseToProduct(ProductInfo productInfo, ProductRequest productRequest) {
        return Product.builder().productName(productInfo.getProductName()).
                productPrice(productInfo.getProductPrice()).
                description(productInfo.getDescription())
                .quantity(productInfo.getQuantity())
                .uniqueCode(productRequest.getUniqueCode())
                .category(productInfo.getCategory())
                .build();
    }
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public ResponseEntity<Object> createProduct(ProductInfo productInfo, ProductRequest productRequest) {
        Model model = new Model();

        if (productRepository.findAllByProductName(productInfo.getProductName()).isPresent()) {
            model.setResult("Product already exist!");
            return ResponseEntity.ok(model.getResult());
        }
        productRepository.save(parseToProduct(productInfo, productRequest));
        model.setResult("Product was created!");
        allQuantity += productInfo.getQuantity();
        totalPrice += productInfo.getProductPrice();
        return ResponseEntity.ok(model.getResult());
    }

    public ProductInfo getProductById(Long id) {
        Product product = checkProductOnExistAndReturn(id);
        return ProductInfo.builder().productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .category(product.getCategory())
                .build();
    }

    public ResponseEntity<Object> updateProductById(Long id, ProductInfo productInfo) {
        Model model = new Model();
        Product product = checkProductOnExistAndReturn(id);

        product.setProductName(productInfo.getProductName());
        product.setProductPrice(productInfo.getProductPrice());
        product.setDescription(productInfo.getDescription());
        product.setCategory(product.getCategory());
        product.setQuantity(productInfo.getQuantity());

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

    public int getAllQuantity() {
        return allQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
