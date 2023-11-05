package com.example.shopp.repository;

import com.example.shopp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findAllByCategoryName(String categoryName);
    void deleteCategoryByCategoryName(String categoryName);
    Optional<Category> findCategoryById(Long id);
    Optional<Category> findAllById(Long id);
    //@Query(add script)
}
