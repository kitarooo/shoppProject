package com.example.shopp.dto;

import com.example.shopp.dto.Enums.MethodOfPurchases;
import com.example.shopp.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class OrderDTO {
    private Long userId;
    private double totalPrice;
    private String address;
    private MethodOfPurchases methodOfPurchases;
    private List<OrderDetailsDTO> details;
}
