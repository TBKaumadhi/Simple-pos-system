package com.myproject.POS_System.service.serviceImpl;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.myproject.POS_System.service.CategoryService;
import com.myproject.POS_System.entity.Category;
import com.myproject.POS_System.Repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired private CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id){
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);

    }

    @Override
    public Category updateCategory(Long id, Category category) {
        
       Category existingCategory = categoryRepository.findById(id).orElse(null);
        if (existingCategory == null) {
            return null;            
        }
        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());
        existingCategory.setUpdatedTime(category.getUpdatedTime());
        return categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(Long categoryId) {
       categoryRepository.deleteById(categoryId);
    }
}
