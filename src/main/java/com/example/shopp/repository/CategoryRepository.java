package com.example.shopp.repository;

import com.example.shopp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findAllByCategoryName(String categoryName);
    void deleteCategoryByCategoryName(String categoryName);
    Optional<Category> findCategoryById(Long id);
    Optional<Category> findAllById(Long id);
}
