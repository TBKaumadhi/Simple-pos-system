package com.myproject.POS_System.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockReqDto {
    
    private Long itemId;
    private int availableStock;
}
