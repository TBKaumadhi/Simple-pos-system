package com.myproject.POS_System.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.POS_System.entity.Category;

public interface CategoryRepository extends JpaRepository <Category, Long> {

}
