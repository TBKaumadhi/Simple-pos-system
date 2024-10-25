/*package com.myproject.POS_System.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;


   @Entity
   @Getter
   @Setter
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long userId;    
        
        @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
        private Cart cart; // One cart per user
    }*/
