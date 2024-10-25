package com.myproject.POS_System.dto;

import java.util.List;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartReqDto {

    
    private List<Long>cartItemIds;  
    private List<Integer> quantities;
}