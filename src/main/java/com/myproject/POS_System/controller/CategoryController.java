package com.myproject.POS_System.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.POS_System.entity.Category;
import com.myproject.POS_System.service.CategoryService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/category")
public class CategoryController {

    @Autowired CategoryService categoryService;
    
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {               
            List<Category> category = categoryService.getAllCategories();
            return ResponseEntity.status(200).body(category);
        }    
       
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.status(201).body(createdCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity <Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {

        Category updatedCategory = categoryService.updateCategory(id, category);        
        return ResponseEntity.status(201).body(updatedCategory);
    }
     @DeleteMapping("/{categoryId}")
    public void deleteCategory (@PathVariable Long categoryId){
        categoryService.deleteCategory(categoryId);
    }
}
