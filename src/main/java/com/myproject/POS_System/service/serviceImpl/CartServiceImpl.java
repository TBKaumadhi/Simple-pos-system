package com.myproject.POS_System.service.serviceImpl;

import java.time.LocalDateTime;
import java.time.Duration;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.myproject.POS_System.CartStatus;

import com.myproject.POS_System.Repository.CartRepository;
import com.myproject.POS_System.Repository.ItemRepository;
import com.myproject.POS_System.Repository.OrderRepository;
import com.myproject.POS_System.Repository.StockRepository;
import com.myproject.POS_System.dto.CartItemReqDto;
import com.myproject.POS_System.dto.CartReqDto;
//import com.myproject.POS_System.dto.CartReqDto;
import com.myproject.POS_System.entity.Cart;

import com.myproject.POS_System.entity.Item;
import com.myproject.POS_System.entity.Stock;
//import com.myproject.POS_System.entity.User;
import com.myproject.POS_System.service.CartService;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final StockRepository stockRepository;
    private final ItemRepository itemRepository;
    
    
    public CartServiceImpl(CartRepository cartRepository, 
                           OrderRepository orderRepository, 
                           StockRepository stockRepository,
                           ItemRepository itemRepository
                           ) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.stockRepository = stockRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    @Transactional
    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

    @Override
    public Cart createCart() {
        Cart cart = new Cart();
       // cart.setUser(user);
        cart.setStatus(CartStatus.ACTIVE);
        return cartRepository.save(cart);
    }
    @Override
    public Cart getCart() {
        Cart activeCart = cartRepository.findFirstByStatusOrderByCreateDateTimeDesc(CartStatus.ACTIVE);
        if (activeCart != null) {
            return activeCart;
        }
        return new Cart();
    }   
  
    @Override
    @Transactional
    public Cart checkOutCart(CartReqDto cartReqDto) {
    Cart cart = getCart();

    List<Long> itemIds = cartReqDto.getCartItemIds();
    List<Integer> quantities = cartReqDto.getQuantities();

double totalCartPrice = 0.0;

 for (int i = 0; i < itemIds.size(); i++) {
 Long itemId = itemIds.get(i);
int quantity = quantities.get(i);

 Item item = itemRepository.findById(itemId).orElseThrow(() -> 
 new IllegalArgumentException("Item not found: " + itemId)
 );

 // Check stock availability
 Stock stock = item.getStock();
if (stock.getAvailable_stock() < quantity) {
 throw new IllegalStateException("Out of stock for item: " + itemId);
 }

 // Add the item to the cart
 cart.getOrderedItems().add(item);


 double itemPrice = calculateItemPrice(item, quantity);
totalCartPrice += itemPrice;

 // Update stock
 stock.setAvailable_stock(stock.getAvailable_stock() - quantity);
 stock.setLastUpdatedDateTime(LocalDateTime.now());
 stockRepository.save(stock);
 }

 // Set the cart's total price and apply discount if applicable
 cart.setTotalCartPrice(totalCartPrice);
 if (totalCartPrice > 10000.00) {
 double discount = 0.1;
 double discountAmount = totalCartPrice * discount;
 cart.setDiscountCartPrice(discountAmount);
 cart.setTotalCartPrice(totalCartPrice - discountAmount);
 }

 cart.setStatus(CartStatus.COMPLETED);
 return cartRepository.save(cart);
}


private double calculateItemPrice(Item item, int quantity) {
 double pricePerItem = item.getDiscount() > 0 
 ? item.getPrice() - item.getDiscount() 
 : item.getPrice();
 return pricePerItem * quantity;
}




@Override
@Transactional
public void deleteCart(Long cartId) {
 Cart cart = cartRepository.findById(cartId)
 .orElseThrow(() -> new RuntimeException("Cart not found"));


 cart.setStatus(CartStatus.CANCELLED);
 cart.setUpDatedDateTime(LocalDateTime.now());
 cartRepository.save(cart); 
}


    @Override
    @Transactional
    public void deleteCartItem(Long itemId, int quantity) {
        Cart cart = getCart();

        
        List <Item> orderedItems =cart.getOrderedItems();
         
        for (Item item : orderedItems) {
            if(item.getId() == itemId){
                Item removeItem = item;
                cart.getOrderedItems().remove(removeItem);
            }
            Stock stock = item.getStock();
            stock.setAvailable_stock(stock.getAvailable_stock() + quantity);
            stockRepository.save(stock);
            
            double removeItemPrice =calculateItemPrice(item, quantity);
            double updateTotalPrice = cart.getTotalCartPrice()- removeItemPrice;
            cart.setTotalCartPrice(updateTotalPrice);

            if (updateTotalPrice <= 10000.00) {
                cart.setDiscountCartPrice(0.0);  // Reset discount if the condition no longer applies
            }
        }    
            cartRepository.save(cart); 
    }
}


