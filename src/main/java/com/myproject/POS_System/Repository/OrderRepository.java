package com.myproject.POS_System.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myproject.POS_System.entity.Order;

public interface OrderRepository extends JpaRepository <Order, Long > {

    @Query("SELECT o FROM Order o ORDER BY o.totalPrice DESC")
    List <Order> findAllOrdersOrderedByTotalPrice();
}
