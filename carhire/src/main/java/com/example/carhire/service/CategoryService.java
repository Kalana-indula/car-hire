package com.example.carhire.service;

import com.example.carhire.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> getAllCategories();
    Category createCategory(Category category);

    Category findCategoryById(Long id);

    Category updateCategory(Long id,Category category);

    Boolean deleteCategory(Long id);

}
