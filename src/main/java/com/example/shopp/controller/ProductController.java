package com.example.shopp.controller;

import com.example.shopp.entity.Category;
import com.example.shopp.entity.Product;
import com.example.shopp.service.Model;
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
    public ResponseEntity<Object> createProduct(Product product) {
        Model model = new Model();
        productService.createProduct(product);
        model.setResult("Product was created!");
        return ResponseEntity.ok(model.getResult());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> findProductById(@PathVariable Long id) {
        Product product = productService.findProductById(id);

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            Model model = new Model();
            model.setResult("Product wasn't found!");
            return ResponseEntity.ok(model.getResult());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateProductById(@PathVariable Long id, String product_name, double product_price, String description, Category category) {
        Model model = new Model();
        productService.updateProductById(id,product_name,product_price,description,category);
        model.setResult("Product was updated");
        return ResponseEntity.ok(model.getResult());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProductById(@PathVariable Long id) {
        Model model = new Model();
        productService.deleteProductById(id);
        model.setResult("Product was deleted!");
        return ResponseEntity.ok(model.getResult());
    }
}
