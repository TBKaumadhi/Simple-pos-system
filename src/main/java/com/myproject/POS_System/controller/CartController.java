package com.myproject.POS_System.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.myproject.POS_System.dto.CartReqDto;
import com.myproject.POS_System.entity.Cart;


//import com.myproject.POS_System.entity.User;
import com.myproject.POS_System.service.CartService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cart")
public class CartController {

 @Autowired CartService cartService;

 @GetMapping()
  public ResponseEntity<List<Cart>> getCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return ResponseEntity.status(201).body(carts);
    }
 
@PostMapping("/create")
public ResponseEntity <Cart> createCart() {
    Cart createdCart = cartService.createCart();
    return ResponseEntity.status(201).body(createdCart);
}

 @PostMapping("/add-item")
 public ResponseEntity <String> completeCart( @RequestBody CartReqDto cartReqDto) {
 cartService.checkOutCart(cartReqDto);

 return ResponseEntity.status(201).body("cart completed");
 }
 
 @DeleteMapping("/{itemid}")
 public void removeCartItem(@PathVariable Long cartItemId, int quantity) {
    cartService.deleteCartItem(cartItemId, quantity);
}


 @DeleteMapping("/{id}")
public void cancelCart (@PathVariable Long cartId){
 cartService.deleteCart(cartId);
 }
}