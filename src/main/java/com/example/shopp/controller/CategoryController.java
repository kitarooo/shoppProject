package com.example.shopp.controller;

import com.example.shopp.dto.info.CategoryInfo;
import com.example.shopp.entity.Category;
import com.example.shopp.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
@Tag(name = "Category controller", description = "For operation with categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/allCategories")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "get all categories", description = "get all categories for ADMIN")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/createCategory")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "create category", description = "for create category by ADMIN")
    public ResponseEntity<Object> createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "get category by id", description = "for get category by id for ADMIN")
    public CategoryInfo getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "update category info", description = "Update category info by id for ADMIN")
    public ResponseEntity<Object> updateCategoryById(@PathVariable Long id, CategoryInfo categoryRequest) {
        return categoryService.updateCategoryById(id, categoryRequest);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "delete category", description = "For delete category by ADMIN")
    public ResponseEntity<Object> deleteCategoryById(@PathVariable Long id) {
        return categoryService.deleteCategoryById(id);
    }
}
