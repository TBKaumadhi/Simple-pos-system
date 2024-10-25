package com.myproject.POS_System.service.serviceImpl;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.myproject.POS_System.OrderStatus;
import com.myproject.POS_System.Repository.OrderRepository;
import com.myproject.POS_System.dto.OrderReqDto;
import com.myproject.POS_System.entity.Cart;
import com.myproject.POS_System.entity.Order;
import com.myproject.POS_System.service.CartService;
import com.myproject.POS_System.service.OrderService;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;

    public OrderServiceImpl(OrderRepository orderRepository, @Lazy CartService cartService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
    }

    @Override
    @Transactional
    public Order createOrder(OrderReqDto orderDto) {
        Cart cart = cartService.getCartById(orderDto.getCartId());
        Order order = new Order();
        order.setTotalPrice(cart.getTotalCartPrice());
        order.setInvoiceNumber("INV" +orderDto.getCartId());
        order.setPaymentmethod(orderDto.getPaymentMethod());
        order.setCart(cart);
        order.setStatus(OrderStatus.COMPLETED);
        cart.setOrder(order); 
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
       return orderRepository.findAllOrdersOrderedByTotalPrice();
    }

    @Override
    @Transactional
    public void cancelOrder(Long orderId) {
    Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));

    if (order.getStatus() == OrderStatus.COMPLETED) {
        throw new IllegalStateException("Cannot cancel a completed order.");
    }

    order.setStatus(OrderStatus.CANCELLED);
    orderRepository.save(order);
}

    
}
