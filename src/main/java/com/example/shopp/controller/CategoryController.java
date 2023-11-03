package com.example.shopp.controller;

import com.example.shopp.model.Category;
import com.example.shopp.model.Product;
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

    @GetMapping("/allCategory")
    public List<Category> findAllCategories() {
        return categoryService.allCategories();
    }

    @PostMapping("/createCategory")
    public ResponseEntity<Object> createCategory(Category category) {
        Model model = new Model();
        categoryService.createCategory(category);
        model.setResult("Category was created!");
        return ResponseEntity.ok(model.getResult());
    }

    /*@GetMapping("/{categoryName}")
    public ResponseEntity<Object> findCategoryByCategoryName(@PathVariable String categoryName) {
        Category category = categoryService.findCategoryByCategoryName(categoryName);
        if (category != null) {
            return ResponseEntity.ok().body(category);
        } else {
            Model model = new Model();
            model.setResult("Category was not found!");
            return ResponseEntity.ok(model.getResult());
        }
    }*/

    @GetMapping("{id}")
    public ResponseEntity<Object> findCategoryById(@PathVariable Long id) {
        Category category = categoryService.findCategoryById(id);

        if (category != null) {
            return ResponseEntity.ok().body(category);
        } else {
            Model model = new Model();
            model.setResult("Category was not found");
            return ResponseEntity.ok(model.getResult());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateCategoryById(@PathVariable Long id, String categoryName) {
        Model model = new Model();
        categoryService.updateCategoryById(id, categoryName);
        model.setResult("Category name was updated!");
        return ResponseEntity.ok(model.getResult());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteCategoryById(@PathVariable Long id) {
        Model model = new Model();
        categoryService.deleteCategoryById(id);
        model.setResult("Category was deleted!");
        return ResponseEntity.ok(model.getResult());
    }

    /*@DeleteMapping("{categoryName}")
    public ResponseEntity<Object> deleteCategoryByCategoryName(@PathVariable String category_name) {
        Model model = new Model();
        categoryService.deleteCategoryByCategoryName(category_name);
        model.setResult("Category with name:" + category_name + ", was deleted");
        return ResponseEntity.ok(model.getResult());
    }*/
}
