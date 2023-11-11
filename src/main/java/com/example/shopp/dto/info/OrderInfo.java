package com.example.shopp.dto.info;

import com.example.shopp.dto.Enums.MethodOfPurchases;
import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderInfo {
    private Long userId;
    private MethodOfPurchases methodOfPurchases;
    private String address;
    private double totalPrice;
}
