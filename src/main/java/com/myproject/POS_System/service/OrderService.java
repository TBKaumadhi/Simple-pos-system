package com.myproject.POS_System.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myproject.POS_System.dto.OrderReqDto;
import com.myproject.POS_System.entity.Order;

@Service
public interface OrderService {
    Order createOrder(OrderReqDto orderDto);
   
    List<Order> getAllOrders();
    void cancelOrder (Long orderId);
    
}
