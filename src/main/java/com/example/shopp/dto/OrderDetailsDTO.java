package com.example.shopp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OrderDetailsDTO {
    private int quantity;
    private Long productId;
    private double totalPrice;

}
