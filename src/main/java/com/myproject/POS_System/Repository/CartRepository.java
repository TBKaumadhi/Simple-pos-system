package com.myproject.POS_System.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.POS_System.CartStatus;
import com.myproject.POS_System.entity.Cart;

public interface CartRepository extends JpaRepository < Cart , Long>{
     List<Cart> findByStatus(CartStatus status);
     
     //Cart findByUserId(Long userId);
     Cart findFirstByStatusOrderByCreateDateTimeDesc(CartStatus status);
     }
