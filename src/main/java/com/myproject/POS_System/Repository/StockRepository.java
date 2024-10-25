package com.myproject.POS_System.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.POS_System.entity.Stock;


public interface StockRepository extends JpaRepository <Stock, Long>{

}
