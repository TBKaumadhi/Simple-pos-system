package com.myproject.POS_System.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemReqDto {
    private Long itemId;
    private int purchased_quantity;
}
