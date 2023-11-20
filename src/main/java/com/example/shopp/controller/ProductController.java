package com.example.shopp.controller;

import com.example.shopp.dto.info.ProductInfo;
import com.example.shopp.entity.Product;
import com.example.shopp.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
@Tag(name = "Product controller", description = "For manipulation with products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/allProducts")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "get all products", description = "Get all products for ADMIN")
    public List<Product> findAllProducts() {
        return productService.findAllProducts();
    }

    @PostMapping("/createProduct")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "create product", description = "Create product by ADMIN for orders ")
    public ResponseEntity<Object> createProduct(@RequestBody ProductInfo productInfo) {
        return productService.createProduct(productInfo);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "get product by id", description = "For get product by id")
    public ProductInfo findProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "update product", description = "For update product by id")
    public ResponseEntity<Object> updateProductById(@PathVariable Long id, ProductInfo productInfo) {
        return productService.updateProductById(id, productInfo);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "delete product", description = "Delete product by id")
    public ResponseEntity<Object> deleteProductById(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }
}
