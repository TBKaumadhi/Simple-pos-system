package com.myproject.POS_System.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    private String description;
    private double price;
    
    @CreationTimestamp
    @Column(nullable = false ,updatable = false)
    private LocalDateTime createdTime;
    @UpdateTimestamp
    private LocalDateTime upDatedTime;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean isActive;

    private double discount;
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name= "category_id")
    private Category category;

    @JsonIgnore
    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    private Stock stock;

    @JsonIgnore
    @ManyToMany(mappedBy="orderedItems")
    private List <Cart> carts;

       
}
