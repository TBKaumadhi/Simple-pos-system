package com.myproject.POS_System.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderReqDto {
    private Long cartId;
    private String paymentMethod;  
}
