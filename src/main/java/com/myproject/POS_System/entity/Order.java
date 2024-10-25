package com.myproject.POS_System.entity;

import java.time.LocalDateTime;

import com.myproject.POS_System.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long orderId;
    //private String userId;    
    private double totalPrice;
    @Enumerated(EnumType.STRING)  
    private OrderStatus status;

    private LocalDateTime orderDateTime;
    @PrePersist
    protected void onCreate(){
        if(this.orderDateTime==null){
            this.orderDateTime=LocalDateTime.now();
        }       
    }
    private String paymentmethod;
    private String invoiceNumber;
    
    @OneToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cartId")
    private Cart cart;


}
