package com.myproject.POS_System.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.POS_System.dto.OrderReqDto;

import com.myproject.POS_System.service.OrderService;
import com.myproject.POS_System.entity.Order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/order")
public class OrderController {

    @Autowired private OrderService orderService;

    @GetMapping
    public ResponseEntity <List <Order> > getAllOrders(){
        List <Order> getOrders = orderService.getAllOrders();
        return ResponseEntity.status(401).body(getOrders);
    } 

    @PostMapping
    public ResponseEntity <Order> createOrderFromCart(@RequestBody OrderReqDto orderDto) {
        Order order = orderService.createOrder(orderDto);
        return ResponseEntity.status(200).body(order);
    }
   
    public ResponseEntity <Order> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
