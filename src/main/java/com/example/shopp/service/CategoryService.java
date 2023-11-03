package com.example.shopp.service;

import com.example.shopp.model.Category;
import com.example.shopp.model.Product;
import com.example.shopp.repository.CategoryRepository;
import com.example.shopp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public List<Category> allCategories() {
        return categoryRepository.findAll();
    }

    public Category createCategory(Category category) {
        categoryRepository.save(category);
        return category;
    }

    public Category findCategoryByCategoryName(String categoryName) {
        return categoryRepository.findAllByCategoryName(categoryName);
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findCategoryById(id).orElse(null);
    }

    public Category updateCategoryById(Long id, String categoryName) {
        Category category = categoryRepository.findAllById(id).orElse(null);
        if (category != null) {
            category.setCategoryName(categoryName);
            categoryRepository.save(category);
        }
        return category;
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    public void deleteCategoryByCategoryName(String categoryName) {
        categoryRepository.deleteCategoryByCategoryName(categoryName);
    }
}
