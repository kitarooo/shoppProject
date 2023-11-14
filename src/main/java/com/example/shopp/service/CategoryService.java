package com.example.shopp.service;

import com.example.shopp.dto.info.CategoryInfo;
import com.example.shopp.entity.Category;
import com.example.shopp.exception.NotFoundException;
import com.example.shopp.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category checkCategoryOnExistAndReturn(Long id) {
        return categoryRepository.findAllByCategoryId(id).orElseThrow(
                () -> new NotFoundException("Category was not found!"));
    }

    public Category parseToCategory(CategoryInfo categoryInfo) {
        return Category.builder().categoryName(categoryInfo.getCategoryName()).build();
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public ResponseEntity<Object> createCategory(CategoryInfo categoryInfo) {
        Model model = new Model();
        if (categoryRepository.findAllByCategoryName(categoryInfo.getCategoryName()).isPresent()) {
            model.setResult("Category already exist!");
            return ResponseEntity.ok(model.getResult());
        }
        categoryRepository.save(parseToCategory(categoryInfo));
        model.setResult("Category was created!");

        return ResponseEntity.ok(model.getResult());
    }

    public CategoryInfo getCategoryById(Long id) {
        Category category = checkCategoryOnExistAndReturn(id);
        return CategoryInfo.builder().categoryName(category.getCategoryName()).build();
    }

    public ResponseEntity<Object> updateCategoryById(Long id, CategoryInfo categoryRequest) {
        Category category = checkCategoryOnExistAndReturn(id);
        Model model = new Model();
        category.setCategoryName(categoryRequest.getCategoryName());
        categoryRepository.save(category);
        model.setResult("Category was updated!");

        return ResponseEntity.ok(model.getResult());
    }

    public ResponseEntity<Object> deleteCategoryById(Long id) {
        Model model = new Model();
        categoryRepository.deleteById(id);
        model.setResult("User was deleted!");

        return ResponseEntity.ok(model.getResult());
    }
}
