package com.myproject.POS_System.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myproject.POS_System.entity.Category;


@Service
public interface CategoryService {

    Category getCategoryById(Long categoryId);

    List<Category> getAllCategories();

    Category createCategory(Category category);

    Category updateCategory(Long id, Category category);

    void deleteCategory(Long categoryId);


}
