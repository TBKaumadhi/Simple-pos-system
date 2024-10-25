package com.myproject.POS_System.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myproject.POS_System.dto.CartItemReqDto;
import com.myproject.POS_System.dto.CartReqDto;
//import com.myproject.POS_System.dto.CartReqDto;
import com.myproject.POS_System.entity.Cart;
//import com.myproject.POS_System.entity.CartItem;


@Service
public interface CartService {
    List <Cart> getAllCarts();
    Cart getCartById(Long cartId);     
    Cart createCart();   
    //Cart getCart(Long userId);
    // CartItem addItem(CartItemReqDto cartItemDto);
    Cart checkOutCart( CartReqDto cartReqDto); 
    void deleteCart(Long cartId);
    Cart getCart();
    void deleteCartItem(Long itemId, int quantity);
    
       
   
 
}
