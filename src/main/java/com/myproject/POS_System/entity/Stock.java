package com.myproject.POS_System.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity

@Getter
@Setter
public class Stock {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int available_stock;
    @UpdateTimestamp
    private LocalDateTime lastUpdatedDateTime;
    
    
    @OneToOne
    @JoinColumn(name = "item_id")  
    private Item item;
}
