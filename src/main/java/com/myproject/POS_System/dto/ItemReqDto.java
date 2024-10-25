package com.myproject.POS_System.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemReqDto {
    private String name;
    private String description;
    private Double price;
    private double discount;   
    private Long categoryId;
    private String imgUrl;
}
