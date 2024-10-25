package com.myproject.POS_System.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myproject.POS_System.CartStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Cart {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long cartId;
    @CreationTimestamp
     private LocalDateTime createDateTime;
    @UpdateTimestamp
    private LocalDateTime upDatedDateTime;

    @Enumerated(EnumType.STRING) 
    private CartStatus status;
    private double discountCartPrice;
    private double totalCartPrice;

   @ManyToMany
    @JoinTable(
        name="cart_item",
        joinColumns= @JoinColumn(name="cartId"),
        inverseJoinColumns= @JoinColumn(name="itemId")
    ) 
    private List <Item> orderedItems =new ArrayList<>();


    @OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
    private Order order;

    /* @OneToOne
    @JoinColumn(name = "user_id", nullable = false) 
    private User user;*/
}
