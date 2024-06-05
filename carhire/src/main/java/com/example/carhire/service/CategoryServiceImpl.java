package com.example.carhire.service;

import com.example.carhire.entity.Category;
import com.example.carhire.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category updateCategory(Long id,Category category) {
        Category existingCategory=categoryRepository.findById(id).orElse(null);

        if(existingCategory!=null){
            existingCategory.setName(category.getName());
            return categoryRepository.save(existingCategory);
        }else{
            return null;
        }
    }

    @Override
    public Boolean deleteCategory(Long id) {
        Boolean isExist=categoryRepository.existsById(id);

        if(isExist){
            categoryRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
