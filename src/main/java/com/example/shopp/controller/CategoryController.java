package com.example.shopp.controller;

import com.example.shopp.dto.CategoryRequest;
import com.example.shopp.entity.Category;
import com.example.shopp.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/allCategories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/createCategory")
    public ResponseEntity<Object> createCategory(CategoryRequest categoryRequest) {
        return categoryService.createCategory(categoryRequest);
    }

    @GetMapping("{id}")
    public CategoryRequest getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateCategoryById(@PathVariable Long id, CategoryRequest categoryRequest) {
        return categoryService.updateCategoryById(id, categoryRequest);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteCategoryById(@PathVariable Long id) {
        return categoryService.deleteCategoryById(id);
    }
}
